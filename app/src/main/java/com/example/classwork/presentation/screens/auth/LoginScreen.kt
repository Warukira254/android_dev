package com.example.classwork.presentation.screens.auth


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.classwork.common.Routes
import com.example.classwork.presentation.MainViewModel
import com.example.classwork.presentation.common.CheckSignedIn
import com.example.classwork.presentation.common.ProgressSpinner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, vm: MainViewModel) {
    CheckSignedIn(vm = vm, navController = navController)
    val focusManager = LocalFocusManager.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val emailState = remember { mutableStateOf(TextFieldValue()) }
            val passState = remember { mutableStateOf(TextFieldValue()) }
//            val roleState = remember { mutableStateOf(emptySet<String>()) }


            Text(
                text = "Login",
                modifier = Modifier.padding(8.dp),
                fontSize = 30.sp,
                fontFamily = FontFamily.Serif
            )
            OutlinedTextField(
                value = emailState.value,
                onValueChange = { emailState.value = it },
                modifier = Modifier.padding(8.dp),
                label = { Text(text = "Email") }
            )
            OutlinedTextField(
                value = passState.value,
                onValueChange = { passState.value = it },
                modifier = Modifier.padding(8.dp),
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            val roles = listOf("Admin", "Service Provider", "User")
            val selectedRoles = remember { mutableStateOf(emptySet<String>()) }
            roles.forEach { role ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        if (selectedRoles.value.contains(role)) {
                            selectedRoles.value -= role
                        } else {
                            selectedRoles.value += role
                        }
                    }
                ) {
                    Checkbox(
                        checked = selectedRoles.value.contains(role),
                        onCheckedChange = { isChecked ->
                            if (isChecked) {
                                selectedRoles.value += role
                            } else {
                                selectedRoles.value -= role
                            }
                        },
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    Text(
                        text = role,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }

            Button(
                onClick = {
                    focusManager.clearFocus()
                    if (selectedRoles.value.isEmpty()) {
                        vm.onLoginError("Please select at least one role")
                    } else {
                        vm.onLogin(emailState.value.text, passState.value.text, selectedRoles.value)
                    }
                },
                modifier = Modifier.padding(8.dp)
            )

            {
                Text(text = "LOGIN")
            }
            Text(
                text = "New here? Go to signup ->",
                color = Color.Blue,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navController.navigate(Routes.Signup.route)
                    }
            )
        }
        val isLoading = vm.inProgress.value
        if (isLoading) {
            ProgressSpinner()
        }
    }
}