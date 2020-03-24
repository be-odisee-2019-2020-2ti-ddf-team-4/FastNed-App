package be.fastned.application.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public interface Scenario {
    void runScenario(AnnotationConfigApplicationContext applicationContext);
}
