package be.odisee.travelbasepakketten;

import be.odisee.travelbasepakketten.dao.PakketRepository;
import be.odisee.travelbasepakketten.domain.Pakket;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class TravelbasepakkettenApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelbasepakkettenApplication.class, args);
    }
}
