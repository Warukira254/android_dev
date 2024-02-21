package com.example.classwork.data

// firebase requires empty constructor
data class UserData(
    var userId: String? = null,
    var name: String? = null,
    var username: String? = null,
    var imageUrl: String? = null,
    var bio: String? = null,
    var role: List<List<String>> = emptyList(), // Initialize with empty list instead of null
    var services: List<String>? = null
) {
    // Convert to map for firebase
    fun toMap() = mapOf(
        "userId" to userId,
        "name" to name,
        "username" to username,
        "imageUrl" to imageUrl,
        "bio" to bio,
        "role" to role,
        "services" to services,
    )
}
