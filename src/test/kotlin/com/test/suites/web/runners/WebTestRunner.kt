package com.test.suites.web.runners

import io.cucumber.junit.CucumberOptions
import net.serenitybdd.cucumber.CucumberWithSerenity
import org.junit.runner.RunWith

@RunWith(CucumberWithSerenity::class)
@CucumberOptions(
    features = ["src/test/resources/features/web"],
    glue = ["com.test.suites.web.stepdefinitions"],
    plugin = ["pretty"],
    tags = "not @ignore"
)
class WebTestRunner
