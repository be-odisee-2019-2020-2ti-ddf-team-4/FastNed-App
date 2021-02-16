package be.odisee.fastned;

import be.odisee.fastned.dao.DocumentatieRepository;
import be.odisee.fastned.domain.Documentatie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity
public class FastnedDocumentatieSecureApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastnedDocumentatieSecureApplication.class, args);
    }

    @Bean
    CommandLineRunner init(DocumentatieRepository repo) {
        return (evt) -> {
            repo.save(new Documentatie("superlader", "45600", "lorem ipsum beschrijving 1"));
            repo.save(new Documentatie("snellader", "45601", "lorem ipsum beschrijving 2"));
            repo.save(new Documentatie("teslalader", "45602", "lorem ipsum beschrijving 3"));
            repo.save(new Documentatie("bmwlader", "45603", "lorem ipsum beschrijving 4"));
        };
    }
}


