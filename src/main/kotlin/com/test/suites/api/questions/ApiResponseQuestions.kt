package com.test.suites.api.questions

import io.restassured.response.Response
import net.serenitybdd.screenplay.Question

/**
 * Punto unico de acceso a la ultima respuesta REST capturada por Serenity.
 *
 * SOLID - SRP: antes esta logica vivia duplicada/mezclada dentro de
 * ApiResponse.kt bajo un objeto interno llamado tambien "SerenityRest".
 * Ahora es un unico helper reutilizable.
 */
object LastApiResponse {
    fun get(): Response = net.serenitybdd.rest.SerenityRest.lastResponse()
}

/**
 * Questions - permiten que un Actor observe la ultima respuesta de la API
 * sin conocer los detalles de REST Assured.
 */
object ApiResponse {

    fun statusCode(): Question<Int> = Question.about("response status code")
        .answeredBy { LastApiResponse.get().statusCode() }

    fun bodyField(fieldPath: String): Question<String?> = Question.about("response field '$fieldPath'")
        .answeredBy { LastApiResponse.get().jsonPath().getString(fieldPath) }

    fun token(): Question<String?> = Question.about("auth token")
        .answeredBy { LastApiResponse.get().jsonPath().getString("token") }

    fun errorMessage(): Question<String?> = Question.about("error message")
        .answeredBy { LastApiResponse.get().jsonPath().getString("error") }

    fun userId(): Question<Int?> = Question.about("user id")
        .answeredBy { LastApiResponse.get().jsonPath().getInt("data.id") }

    fun userEmail(): Question<String?> = Question.about("user email")
        .answeredBy { LastApiResponse.get().jsonPath().getString("data.email") }

    fun userFirstName(): Question<String?> = Question.about("user first name")
        .answeredBy { LastApiResponse.get().jsonPath().getString("data.first_name") }

    fun userAvatar(): Question<String?> = Question.about("user avatar")
        .answeredBy { LastApiResponse.get().jsonPath().getString("data.avatar") }
}
