package com.test.suites.web.stepdefinitions

import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import com.test.suites.web.questions.CartBadgeCount
import com.test.suites.web.questions.CurrentPageTitle
import com.test.suites.web.questions.LoginErrorMessage
import com.test.suites.web.questions.OrderConfirmationMessage
import com.test.suites.web.tasks.AddBackpackToCart
import com.test.suites.web.tasks.AddBikeLightToCart
import com.test.suites.web.tasks.CompletePurchase
import com.test.suites.web.tasks.GoToCart
import com.test.suites.web.tasks.LoginAs
import net.serenitybdd.screenplay.GivenWhenThen.seeThat
import net.serenitybdd.screenplay.actors.OnStage
import net.serenitybdd.screenplay.actors.OnlineCast
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.equalTo

class WebSteps {

    @Before
    fun setStage() {
        OnStage.setTheStage(OnlineCast())
    }

    @Given("Ana is a registered user")
    fun anaIsARegisteredUser() {
        OnStage.theActorCalled("Ana")
    }

    @Given("Ana is logged in as a standard user")
    fun anaIsLoggedInAsStandardUser() {
        OnStage.theActorCalled("Ana").attemptsTo(
            LoginAs("standard_user", "secret_sauce")
        )
    }

    @When("she logs in with username {string} and password {string}")
    fun sheLogsInWith(username: String, password: String) {
        OnStage.theActorInTheSpotlight().attemptsTo(
            LoginAs(username, password)
        )
    }

    @When("she adds the Sauce Labs Backpack to the cart")
    fun sheAddsBackpackToCart() {
        OnStage.theActorInTheSpotlight().attemptsTo(
            AddBackpackToCart()
        )
    }

    @When("she adds the Bike Light to the cart")
    fun sheAddsBikeLightToCart() {
        OnStage.theActorInTheSpotlight().attemptsTo(
            AddBikeLightToCart()
        )
    }

    @When("she goes to the cart")
    fun sheGoesToTheCart() {
        OnStage.theActorInTheSpotlight().attemptsTo(
            GoToCart()
        )
    }

    @When("she completes the purchase with first name {string}, last name {string} and postal code {string}")
    fun sheCompletesThePurchase(
        firstName: String,
        lastName: String,
        postalCode: String
    ) {
        OnStage.theActorInTheSpotlight().attemptsTo(
            CompletePurchase(firstName, lastName, postalCode)
        )
    }

    @Then("she should see the inventory page title {string}")
    fun sheShouldSeeInventoryTitle(expectedTitle: String) {
        OnStage.theActorInTheSpotlight().should(
            seeThat(CurrentPageTitle, equalTo(expectedTitle))
        )
    }

    @Then("the cart badge should show {string}")
    fun cartBadgeShouldShow(expectedCount: String) {
        OnStage.theActorInTheSpotlight().should(
            seeThat(CartBadgeCount, equalTo(expectedCount))
        )
    }

    @Then("she should see the order confirmation message {string}")
    fun sheShouldSeeConfirmation(expectedMessage: String) {
        OnStage.theActorInTheSpotlight().should(
            seeThat(OrderConfirmationMessage, containsString(expectedMessage))
        )
    }

    @Then("she should see the error message {string}")
    fun sheShouldSeeErrorMessage(expectedMessage: String) {
        OnStage.theActorInTheSpotlight().should(
            seeThat(LoginErrorMessage, containsString(expectedMessage))
        )
    }
}