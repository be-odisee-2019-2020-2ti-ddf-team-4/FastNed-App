package be.odisee.fastned;

import be.odisee.fastned.dao.ProbleemRepository;
import be.odisee.fastned.domain.Probleem;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity
public class FastnedProbleemSecureApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastnedProbleemSecureApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ProbleemRepository repo) {
		return (evt) -> {
			repo.save(new Probleem("De laadpaal staat in brand","opgelost", "blus het vuur"));
			repo.save(new Probleem("laadpaal is geschaafd", "opgelost", "verf de paal"));
			repo.save(new Probleem("error 4040", "opgelost", "start de laadpaal opnieuw op"));
		};
	}
}
