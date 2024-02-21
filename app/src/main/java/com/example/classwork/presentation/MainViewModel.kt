package com.example.classwork.presentation

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classwork.common.USERS
import com.example.classwork.data.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val auth: FirebaseAuth,
    val db: FirebaseFirestore,
    val storage: FirebaseStorage
) : ViewModel() {

    val signedIn = mutableStateOf(false)
    val inProgress = mutableStateOf(false)
    val userData = mutableStateOf<UserData?>(null)
    val popupNotification = mutableStateOf<String?>(null)

    init {
        auth.signOut()
        val currentUser = auth.currentUser
        signedIn.value = currentUser != null
        currentUser?.let {
            getUserData(it.uid)
        }
    }

    suspend fun onSignup(username: String, email: String, pass: String, role: List<String>): Boolean {
        if (username.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            popupNotification.value = "Please fill in all the fields"
            return false
        }
        inProgress.value = true
        return try {
            val documents = db.collection(USERS).whereEqualTo("username", username).get().await()
            if (documents.size() > 0) {
                handleException(customMessage = "Username already exists")
                false
            } else {
                auth.createUserWithEmailAndPassword(email, pass).await().user?.let { user ->
                    createOrUpdateProfile(username = username, role = role)
                    val newUser = UserData(
                        userId = user.uid,
                        name = username,
                        username = username,
                        role = listOf(role) // Assuming the default role is USER
                    )
                    saveUserData(newUser)
                    true
                } ?: run {
                    handleException(null, "Signup failed")
                    false
                }
            }
        } catch (e: Exception) {
            handleException(e, "Signup failed")
            false
        } finally {
            inProgress.value = false
        }
    }

    fun onLogin(email: String, pass: String, roles: Set<String>) {
        inProgress.value = true
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        getUserData(currentUser.uid)
                    } else {
                        handleException(null, "Current user is null")
                    }
                } else {
                    handleException(task.exception, "Login failed")
                }
                inProgress.value = false
            }
            .addOnFailureListener { exc ->
                handleException(exc, "Login failed")
                inProgress.value = false
            }
    }

    private fun createOrUpdateProfile(
        name: String? = null,
        username: String? = null,
        role: List<String>? = null
    ) {
        val uid = auth.currentUser?.uid
        val userData = UserData(
            userId = uid,
            name = name ?: userData.value?.name,
            username = username ?: userData.value?.username,
//            role = role ?: userData.value?.role ?: listOf() // Ensure the role is not null
        )
        uid?.let { uid ->
            viewModelScope.launch {
                inProgress.value = true
                try {
                    db.collection(USERS).document(uid).set(userData).await()
                    this@MainViewModel.userData.value = userData
                } catch (e: Exception) {
                    handleException(e, "Profile update failed")
                } finally {
                    inProgress.value = false
                }
            }
        }
    }

    fun getUserData(uid: String) {
        inProgress.value = true
        db.collection(USERS).document(uid).get()
            .addOnSuccessListener { document ->
                val user = document.toObject<UserData>()
                userData.value = user
                inProgress.value = false
            }
            .addOnFailureListener { exc ->
                handleException(exc, "Cannot get user data")
                inProgress.value = false
            }
    }

    private fun handleException(exception: Exception? = null, customMessage: String = "") {
        exception?.printStackTrace()
        val errorMsg = exception?.localizedMessage ?: ""
        val message = if (customMessage.isEmpty()) errorMsg else "$customMessage: $errorMsg"
        popupNotification.value = message
    }
    fun uploadProfileImage(it: Uri) = viewModelScope.launch {
        inProgress.value = true
        val currentUser = auth.currentUser
        val currentUserData = userData.value
        if (currentUser != null && currentUserData != null) {
            val storageRef = storage.reference.child("profile_images/${currentUser.uid}/profile.jpg")
            storageRef.putFile(it)
                .addOnSuccessListener {
                    // Image uploaded successfully
                    // Get the download URL and update the user profile
                    storageRef.downloadUrl.addOnSuccessListener { uri ->
                        val user = currentUserData.copy(imageUrl = uri.toString())
                        saveUserData(user) // Call saveUserData directly
                    }
                }
                .addOnFailureListener {
                    // Handle failure
                    handleException(it, "Image upload failed")
                }
        }
    }

    private fun saveUserData(userData: UserData) {
        try {
            val userId = userData.userId ?: return
            db.collection(USERS)
                .document(userId)
                .set(userData)
                .addOnSuccessListener {
                    Log.d(TAG, "User data saved successfully")
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Error saving user data: $e")
                }
        } catch (e: Exception) {
            Log.e(TAG, "Error saving user data: $e")
        }
    }


    fun onLogout() {
        auth.signOut()
        signedIn.value = false
        userData.value = null
        popupNotification.value = "Logged out"
    }

    fun onLoginError(s: String) {
        popupNotification.value = s
    }


    fun updateProfileData(name: String, username: String, bio: String) {
        // TODO: Implement profile data update
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}