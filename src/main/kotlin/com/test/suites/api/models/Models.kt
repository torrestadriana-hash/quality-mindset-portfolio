package com.test.suites.api.models

data class AuthRequest(
    val email: String,
    val password: String? = null
)

data class AuthResponse(
    val id: Int? = null,
    val token: String? = null,
    val error: String? = null
)

data class UserData(
    val id: Int? = null,
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val avatar: String? = null
)

data class UserResponse(
    val data: UserData? = null,
    val support: SupportData? = null
)

data class SupportData(
    val url: String? = null,
    val text: String? = null
)
