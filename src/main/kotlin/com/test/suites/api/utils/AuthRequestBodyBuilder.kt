package com.test.suites.api.utils

import com.test.suites.api.models.AuthRequest

/**
 * Construye el cuerpo JSON de una peticion de autenticacion (login/register).
 *
 * SOLID - SRP: unica responsabilidad: serializar un [AuthRequest].
 * DRY: antes esta logica estaba duplicada en LoginUser y RegisterUser;
 * ahora ambos Tasks reutilizan esta misma funcion.
 */
object AuthRequestBodyBuilder {

    fun build(authRequest: AuthRequest): String =
        if (authRequest.password != null) {
            """{"email":"${authRequest.email}","password":"${authRequest.password}"}"""
        } else {
            """{"email":"${authRequest.email}"}"""
        }
}
