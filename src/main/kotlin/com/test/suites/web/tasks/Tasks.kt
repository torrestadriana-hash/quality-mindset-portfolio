package com.test.suites.web.tasks

import net.serenitybdd.core.Serenity
import com.test.suites.web.config.WebEndpoints
import com.test.suites.web.ui.CartPage
import com.test.suites.web.ui.CheckoutPage
import com.test.suites.web.ui.InventoryPage
import com.test.suites.web.ui.LoginPage
import net.serenitybdd.core.annotations.findby.By
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.actions.Click
import net.serenitybdd.screenplay.actions.Enter
import net.serenitybdd.screenplay.actions.Open
import net.serenitybdd.screenplay.targets.Target
import net.serenitybdd.screenplay.conditions.Check
import net.serenitybdd.screenplay.actions.SelectFromOptions

open class LoginAs(private val username: String, private val password: String) : Task {

    override fun <T : Actor> performAs(actor: T) {
        actor.attemptsTo(
            PrepareCleanSession()
        )

        actor.attemptsTo(
            Open.url(WebEndpoints.BASE_URL),
            Enter.theValue(username).into(LoginPage.USERNAME_FIELD),
            Enter.theValue(password).into(LoginPage.PASSWORD_FIELD),
            Click.on(LoginPage.LOGIN_BUTTON)
        )

        actor.attemptsTo(
            HandlePossiblePopups()
        )

        Serenity.takeScreenshot()
    }
}

open class AddBackpackToCart : Task {
    override fun <T : Actor> performAs(actor: T) {
        actor.attemptsTo(Click.on(InventoryPage.ADD_TO_CART_BACKPACK))
    }
}

open class AddBikeLightToCart : Task {
    override fun <T : Actor> performAs(actor: T) {
        actor.attemptsTo(Click.on(InventoryPage.ADD_TO_CART_BIKE))
    }
}

open class GoToCart : Task {
    override fun <T : Actor> performAs(actor: T) {
        actor.attemptsTo(Click.on(InventoryPage.CART_ICON))
    }
}

open class CompletePurchase(
    private val firstName: String,
    private val lastName: String,
    private val postalCode: String
) : Task {
    override fun <T : Actor> performAs(actor: T) {
        actor.attemptsTo(
            Click.on(CartPage.CHECKOUT_BUTTON),
            Enter.theValue(firstName).into(CheckoutPage.FIRST_NAME),
            Enter.theValue(lastName).into(CheckoutPage.LAST_NAME),
            Enter.theValue(postalCode).into(CheckoutPage.POSTAL_CODE),
            Click.on(CheckoutPage.CONTINUE_BUTTON),
            Click.on(CheckoutPage.FINISH_BUTTON)
        )
    }
}

open class HandlePossiblePopups : Task {

    override fun <T : Actor> performAs(actor: T) {

        val cookieButton = Target.the("cookies button")
            .located(By.cssSelector("button[aria-label='Accept all']"))

        val closeChangePassword = Target.the("close change password")
            .located(By.cssSelector("button.close, button[aria-label='Close']"))

        try {
            val cookie = cookieButton.resolveFor(actor)
            if (cookie.isDisplayed) {
                actor.attemptsTo(Click.on(cookieButton))
            }
        } catch (e: Exception) {}

        repeat(3) {
            try {
                val close = closeChangePassword.resolveFor(actor)
                if (close.isDisplayed) {
                    actor.attemptsTo(Click.on(closeChangePassword))
                    return
                }
            } catch (e: Exception) {
                Thread.sleep(500)
            }
        }
    }
}