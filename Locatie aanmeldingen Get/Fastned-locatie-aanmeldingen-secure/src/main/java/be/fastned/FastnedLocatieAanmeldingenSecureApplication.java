package be.fastned;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Component;

import be.fastned.dao.LocatieAanmeldingRepository;
import be.fastned.domain.LocatieAanmelding;

@Component
@SpringBootApplication
@EnableGlobalMethodSecurity
public class FastnedLocatieAanmeldingenSecureApplication extends RepositoryRestConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(FastnedLocatieAanmeldingenSecureApplication.class, args);
	}
	
	
	

	 @Override
	 public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
	    config.getCorsRegistry().addMapping("/**")
	            .allowedOrigins("http://localhost:8888","http://localhost:8889");
	  }
	
	// locatie initaties
		@Bean
		CommandLineRunner init(LocatieAanmeldingRepository repo) {
			
			return (evt) -> {
				repo.save(new LocatieAanmelding("Oliver","Brown","Ikea straat", 69, "Brussel", "1000"));
				repo.save(new LocatieAanmelding("Harry","Thomas","Mc Donals straat", 70, "Brussel", "1000"));
				repo.save(new LocatieAanmelding("Amy","Angevin","Kinepolis straat", 71, "Gent", "9000"));
				repo.save(new LocatieAanmelding("Jake","Stuart","Sport Oase straat", 72, "Antwerpen", "2000"));
				repo.save(new LocatieAanmelding("Ethan","Bruce","Shopping straat", 73, "Mechelen", "2800"));

				
				
			};
		}

}
