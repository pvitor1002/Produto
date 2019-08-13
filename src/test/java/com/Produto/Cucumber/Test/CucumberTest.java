package com.Produto.Cucumber.Test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", glue = {"com.Produto.Cucumber.Test.StepDef", "com.Produto.Cucumber.Test.Config"})
public class CucumberTest {

}
