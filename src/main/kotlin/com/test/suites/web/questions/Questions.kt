package com.test.suites.web.questions

import com.test.suites.web.ui.CheckoutPage
import com.test.suites.web.ui.InventoryPage
import com.test.suites.web.ui.LoginPage
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Question
import net.serenitybdd.screenplay.questions.Text
import net.serenitybdd.screenplay.questions.Visibility

object CurrentPageTitle : Question<String> {
    override fun answeredBy(actor: Actor): String =
        Text.of(InventoryPage.PAGE_TITLE).answeredBy(actor)
}

object CartBadgeCount : Question<String> {

    override fun answeredBy(actor: Actor): String {

        return InventoryPage.CART_BADGE
            .resolveAllFor(actor)
            .firstOrNull()
            ?.text ?: "0"
    }
}

object OrderConfirmationMessage : Question<String> {
    override fun answeredBy(actor: Actor): String =
        Text.of(CheckoutPage.SUCCESS_HEADER).answeredBy(actor)
}

object LoginErrorMessage : Question<String> {
    override fun answeredBy(actor: Actor): String =
        Text.of(LoginPage.ERROR_MESSAGE).answeredBy(actor)
}

object FirstProductName : Question<String> {
    override fun answeredBy(actor: Actor): String =
        Text.of(InventoryPage.FIRST_PRODUCT_NAME).answeredBy(actor)
}