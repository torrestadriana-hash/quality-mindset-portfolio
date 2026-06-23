package com.test.suites.api.abilities

import com.test.suites.api.config.ApiEndpoints
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.rest.abilities.CallAnApi

/**
 * Fabrica de habilidades (Screenplay) para la suite API.
 *
 * SOLID - DIP: los Steps dependen de esta abstraccion para dotar a un
 * Actor de la capacidad de llamar a la API, en lugar de instanciar
 * `CallAnApi` directamente en cada step definition.
 */
object ApiAbilities {

    fun callApi(baseUrl: String = ApiEndpoints.BASE_URL): CallAnApi =
        CallAnApi.at(baseUrl)

    fun Actor.withApiAbility(baseUrl: String = ApiEndpoints.BASE_URL): Actor =
        this.also { it.whoCan(callApi(baseUrl)) }
}
