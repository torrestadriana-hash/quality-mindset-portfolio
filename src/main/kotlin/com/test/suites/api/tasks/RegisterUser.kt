package com.test.suites.api.tasks

import com.test.suites.api.config.ApiEndpoints
import com.test.suites.api.models.AuthRequest
import com.test.suites.api.utils.AuthRequestBodyBuilder
import com.test.suites.api.utils.withJsonBody
import com.test.suites.common.logging.Loggers
import net.serenitybdd.annotations.Step
import net.serenitybdd.rest.SerenityRest
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.Tasks
import net.serenitybdd.screenplay.rest.interactions.Post

/**
 * Task - intenta registrar a un nuevo usuario.
 */
open class RegisterUser(
    private val authRequest: AuthRequest
) : Task {

    @Step("{0} attempts to register with email '#authRequest.email'")
    override fun <T : Actor> performAs(actor: T) {
        val body = AuthRequestBodyBuilder.build(authRequest)

        logger.info("[{}] POST {}", actor.name, ApiEndpoints.REGISTER)
        logger.info("[{}] Request Body: {}", actor.name, body)

        actor.attemptsTo(
            Post.to(ApiEndpoints.REGISTER)
                .with { request -> request.withJsonBody(body) }
        )

        val response = SerenityRest.lastResponse()

        logger.info("[{}] Response Status: {}", actor.name, response.statusCode)
        logger.info("[{}] Response Body: {}", actor.name, response.body.asPrettyString())

        if (response.statusCode >= 400) {
            logger.error(
                "[{}] Request failed. Status: {}, Response: {}",
                actor.name,
                response.statusCode,
                response.body.asPrettyString()
            )
        }
    }

    companion object {
        private val logger = Loggers.of(RegisterUser::class.java)

        fun withCredentials(email: String, password: String): RegisterUser =
            Tasks.instrumented(RegisterUser::class.java, AuthRequest(email, password))

        fun withEmailOnly(email: String): RegisterUser =
            Tasks.instrumented(RegisterUser::class.java, AuthRequest(email))
    }
}
