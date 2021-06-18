package org.formation;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "json:target/cucumber.json", "pretty", "html:target/cucumber-reports.html" })
public class RunCucumberTest {
}
