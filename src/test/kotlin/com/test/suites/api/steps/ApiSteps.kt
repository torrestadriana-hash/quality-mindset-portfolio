package com.test.suites.api.steps

import io.cucumber.java.Before
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import com.test.suites.api.abilities.ApiAbilities.withApiAbility
import com.test.suites.api.config.ApiEndpoints
import com.test.suites.api.questions.ApiResponse
import com.test.suites.api.questions.LastApiResponse
import com.test.suites.api.tasks.GetUser
import com.test.suites.api.tasks.LoginUser
import com.test.suites.api.tasks.RegisterUser
import net.serenitybdd.screenplay.actors.OnStage
import net.serenitybdd.screenplay.actors.OnlineCast
import org.assertj.core.api.Assertions.assertThat

class ApiSteps {

    @Before
    fun setUp() {
        OnStage.setTheStage(OnlineCast())
        OnStage.theActorCalled("ApiConsumer").withApiAbility()
    }

    @Given("the API is available at {string}")
    fun theApiIsAvailableAt(expectedBaseUrl: String) {
        assertThat(expectedBaseUrl)
            .`as`("Expected configured API base URL to match the one used by the suite")
            .isEqualTo(ApiEndpoints.BASE_URL)
    }

    @When("I register with email {string} and password {string}")
    fun iRegisterWithEmailAndPassword(email: String, password: String) {
        OnStage.theActorInTheSpotlight().attemptsTo(RegisterUser.withCredentials(email, password))
    }

    @When("I register with email {string} without a password")
    fun iRegisterWithEmailOnly(email: String) {
        OnStage.theActorInTheSpotlight().attemptsTo(RegisterUser.withEmailOnly(email))
    }

    @When("I register without any credentials")
    fun iRegisterWithoutCredentials() {
        OnStage.theActorInTheSpotlight().attemptsTo(RegisterUser.withEmailOnly(""))
    }

    @When("I login with email {string} and password {string}")
    fun iLoginWithEmailAndPassword(email: String, password: String) {
        OnStage.theActorInTheSpotlight().attemptsTo(LoginUser.withCredentials(email, password))
    }

    @When("I login with email {string} without a password")
    fun iLoginWithEmailOnly(email: String) {
        OnStage.theActorInTheSpotlight().attemptsTo(LoginUser.withEmailOnly(email))
    }

    @When("I request user with id {int}")
    fun iRequestUserWithId(userId: Int) {
        OnStage.theActorInTheSpotlight().attemptsTo(GetUser.withId(userId))
    }

    @Then("the response status code should be {int}")
    fun theResponseStatusCodeShouldBe(expectedStatusCode: Int) {
        val actualStatusCode = OnStage.theActorInTheSpotlight().asksFor(ApiResponse.statusCode())

        assertThat(actualStatusCode)
            .`as`("Expected status code $expectedStatusCode but got $actualStatusCode")
            .isEqualTo(expectedStatusCode)
    }

    @And("the response should contain a valid token")
    fun theResponseShouldContainAValidToken() {
        val token = OnStage.theActorInTheSpotlight().asksFor(ApiResponse.token())

        assertThat(token).isNotNull.isNotEmpty
    }

    @And("the response should contain a user id")
    fun theResponseShouldContainAUserId() {
        val id = LastApiResponse.get().jsonPath().getString("id")

        assertThat(id).isNotNull
    }

    @And("the response should contain the error message {string}")
    fun theResponseShouldContainErrorMessage(expectedError: String) {
        val error = OnStage.theActorInTheSpotlight().asksFor(ApiResponse.errorMessage())

        assertThat(error).isEqualTo(expectedError)
    }

    @And("the response should contain the user id {int}")
    fun theResponseShouldContainUserId(expectedId: Int) {
        val userId = OnStage.theActorInTheSpotlight().asksFor(ApiResponse.userId())

        assertThat(userId).isEqualTo(expectedId)
    }

    @And("the response should contain a valid email")
    fun theResponseShouldContainAValidEmail() {
        val email = OnStage.theActorInTheSpotlight().asksFor(ApiResponse.userEmail())

        assertThat(email).isNotNull.contains("@")
    }

    @And("the response should contain a first name")
    fun theResponseShouldContainAFirstName() {
        val firstName = OnStage.theActorInTheSpotlight().asksFor(ApiResponse.userFirstName())

        assertThat(firstName).isNotNull.isNotEmpty
    }

    @And("the response should contain a valid avatar url")
    fun theResponseShouldContainAValidAvatarUrl() {
        val avatar = OnStage.theActorInTheSpotlight().asksFor(ApiResponse.userAvatar())

        assertThat(avatar).isNotNull.startsWith("https://")
    }
}
