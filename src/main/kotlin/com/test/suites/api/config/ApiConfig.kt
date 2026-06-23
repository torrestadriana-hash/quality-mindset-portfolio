package com.test.suites.api.config

/**
 * Configuracion centralizada de la suite API.
 *
 * SOLID - SRP: este objeto es la unica fuente de verdad para endpoints,
 * cabeceras y datos de prueba de la API. Tasks y Questions dependen de
 * estas constantes en vez de declarar valores propios (DRY).
 */
object ApiEndpoints {
    const val BASE_URL = "https://reqres.in/api"
    const val REGISTER = "/register"
    const val LOGIN = "/login"
    const val USERS = "/users"
}

object ApiHeaders {
    const val CONTENT_TYPE = "application/json"
    const val API_KEY = "pro_15ce5231333fc4c6b622e47d4c1731f5eb27fe1dc5ca254578785fc284c04865"
}

object ApiTestData {
    const val VALID_EMAIL = "eve.holt@reqres.in"
    const val VALID_PASSWORD = "pistol"
    const val UNREGISTERED_EMAIL = "notfound@reqres.in"
    const val EXISTING_USER_ID = 2
    const val NONEXISTENT_USER_ID = 23
}
