package com.test.suites.api.utils

import com.test.suites.api.config.ApiHeaders
import io.restassured.specification.RequestSpecification

/**
 * Aplica las cabeceras comunes de la API a una peticion REST Assured.
 *
 * SOLID - OCP: cualquier Task nuevo (GET/POST/PUT/DELETE) puede reutilizar
 * estos extension functions sin duplicar la configuracion de cabeceras.
 */
fun RequestSpecification.withCommonApiHeaders(): RequestSpecification =
    this.header("x-api-key", ApiHeaders.API_KEY)
        .relaxedHTTPSValidation()

fun RequestSpecification.withJsonBody(body: String): RequestSpecification =
    this.contentType(ApiHeaders.CONTENT_TYPE)
        .withCommonApiHeaders()
        .body(body)
