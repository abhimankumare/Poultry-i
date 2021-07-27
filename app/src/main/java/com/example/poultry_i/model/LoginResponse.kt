package com.example.poultry_i.model


data class LoginResponse(
    var token: String? = null,
    var message: String? = null,
    var success: String? = null,
    val user : User? = null
)