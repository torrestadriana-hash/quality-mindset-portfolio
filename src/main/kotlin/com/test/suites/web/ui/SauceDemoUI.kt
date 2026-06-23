package com.test.suites.web.ui

import net.serenitybdd.screenplay.targets.Target

object LoginPage {
    val USERNAME_FIELD: Target = Target.the("username field").locatedBy("#user-name")
    val PASSWORD_FIELD: Target = Target.the("password field").locatedBy("#password")
    val LOGIN_BUTTON: Target = Target.the("login button").locatedBy("#login-button")
    val ERROR_MESSAGE: Target = Target.the("error message").locatedBy("[data-test='error']")
}

object InventoryPage {
    val PAGE_TITLE: Target = Target.the("page title").locatedBy(".title")
    val CART_BADGE: Target = Target.the("cart badge").locatedBy(".shopping_cart_badge")
    val CART_ICON: Target = Target.the("cart icon").locatedBy(".shopping_cart_link")
    val SORT_DROPDOWN: Target = Target.the("sort dropdown").locatedBy("[data-test='product-sort-container']")
    val FIRST_PRODUCT_NAME: Target = Target.the("first product name").locatedBy(".inventory_item_name:first-of-type")
    val ADD_TO_CART_BACKPACK: Target = Target.the("add Sauce Labs Backpack to cart")
        .locatedBy("[data-test='add-to-cart-sauce-labs-backpack']")
    val ADD_TO_CART_BIKE: Target = Target.the("add Bike Light to cart")
        .locatedBy("[data-test='add-to-cart-sauce-labs-bike-light']")
}

object CartPage {
    val CHECKOUT_BUTTON: Target = Target.the("checkout button").locatedBy("[data-test='checkout']")
}

object CheckoutPage {
    val FIRST_NAME: Target = Target.the("first name field").locatedBy("[data-test='firstName']")
    val LAST_NAME: Target = Target.the("last name field").locatedBy("[data-test='lastName']")
    val POSTAL_CODE: Target = Target.the("postal code field").locatedBy("[data-test='postalCode']")
    val CONTINUE_BUTTON: Target = Target.the("continue button").locatedBy("[data-test='continue']")
    val FINISH_BUTTON: Target = Target.the("finish button").locatedBy("[data-test='finish']")
    val SUCCESS_HEADER: Target = Target.the("success header").locatedBy(".complete-header")
}