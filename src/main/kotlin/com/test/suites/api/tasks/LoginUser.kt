package com.test.suites.api.tasks

import com.test.suites.api.config.ApiEndpoints
import com.test.suites.api.models.AuthRequest
import com.test.suites.api.utils.AuthRequestBodyBuilder
import com.test.suites.api.utils.withJsonBody
import net.serenitybdd.annotations.Step
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.Tasks
import net.serenitybdd.screenplay.rest.interactions.Post

/**
 * Task - intenta autenticar a un usuario.
 */
open class LoginUser(
    private val authRequest: AuthRequest
) : Task {

    @Step("{0} attempts to login with email '#authRequest.email'")
    override fun <T : Actor> performAs(actor: T) {
        val body = AuthRequestBodyBuilder.build(authRequest)

        actor.attemptsTo(
            Post.to(ApiEndpoints.LOGIN)
                .with { request -> request.withJsonBody(body) }
        )
    }

    companion object {
        fun withCredentials(email: String, password: String): LoginUser =
            Tasks.instrumented(LoginUser::class.java, AuthRequest(email, password))

        fun withEmailOnly(email: String): LoginUser =
            Tasks.instrumented(LoginUser::class.java, AuthRequest(email))
    }
}
