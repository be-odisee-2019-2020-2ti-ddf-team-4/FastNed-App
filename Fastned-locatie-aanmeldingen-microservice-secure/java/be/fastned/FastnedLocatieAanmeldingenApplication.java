package be.fastned;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import be.fastned.dao.LocatieRepository;
import be.fastned.domain.Locatie;

@SpringBootApplication
public class FastnedLocatieAanmeldingenApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastnedLocatieAanmeldingenApplication.class, args);
	}
	
	// locatie initaties
	@Bean
	CommandLineRunner init(LocatieRepository repo) {
		
		return (evt) -> {
			repo.save(new Locatie("Ikea straat", 69, "Brussel", "1000"));
			repo.save(new Locatie("Mc Donals straat", 70, "Brussel", "1000"));
			repo.save(new Locatie("Kinepolis straat", 71, "Gent", "9000"));
			repo.save(new Locatie("Sport Oase straat", 72, "Antwerpen", "2000"));
			repo.save(new Locatie("Shopping straat", 73, "Mechelen", "2800"));

			
			
		};
	}

}
