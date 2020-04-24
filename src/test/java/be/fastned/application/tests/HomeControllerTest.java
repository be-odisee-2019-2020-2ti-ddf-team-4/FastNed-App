package be.fastned.application.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format={"pretty", "html:target/cucumber"}, tags={"~@skip"})
public class HomeControllerTest {

    public static void main(String[] args) {
        System.out.println("test");
    }
}