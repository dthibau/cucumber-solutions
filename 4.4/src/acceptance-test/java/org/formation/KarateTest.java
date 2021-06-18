package org.formation;

import org.junit.runner.RunWith;

import com.intuit.karate.junit4.Karate;

import io.cucumber.junit.CucumberOptions;

@RunWith(Karate.class)
@CucumberOptions(plugin = {"pretty", "json:target/karate-cucumber.json"}, features = "classpath:org/formation/karate")
public class KarateTest {

}
