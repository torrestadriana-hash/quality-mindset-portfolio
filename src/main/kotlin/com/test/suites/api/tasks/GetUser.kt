package com.test.suites.api.tasks

import com.test.suites.api.config.ApiEndpoints
import com.test.suites.api.utils.withCommonApiHeaders
import net.serenitybdd.annotations.Step
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.Tasks
import net.serenitybdd.screenplay.rest.interactions.Get

/**
 * Task - obtiene los datos de un usuario por su id.
 */
open class GetUser(
    private val userId: Int
) : Task {

    @Step("{0} retrieves user with id '#userId'")
    override fun <T : Actor> performAs(actor: T) {
        actor.attemptsTo(
            Get.resource("${ApiEndpoints.USERS}/$userId")
                .with { request -> request.withCommonApiHeaders() }
        )
    }

    companion object {
        fun withId(userId: Int): GetUser =
            Tasks.instrumented(GetUser::class.java, userId)
    }
}
