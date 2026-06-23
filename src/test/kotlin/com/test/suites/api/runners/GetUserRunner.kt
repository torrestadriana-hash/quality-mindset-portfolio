package com.test.suites.api.runners

import io.cucumber.junit.CucumberOptions
import net.serenitybdd.cucumber.CucumberWithSerenity
import org.junit.runner.RunWith

@RunWith(CucumberWithSerenity::class)
@CucumberOptions(
    features = ["src/test/resources/features/api/getuser"],
    glue = ["com.test.suites.api.steps"],
    plugin = ["pretty"],
    tags = "not @ignore"
)
class GetUserRunner
