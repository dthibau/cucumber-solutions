package org.formation;



import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = { ProductServiceApplication.class }, webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringIntegrationTest {
}
