package be.odisee.fastned;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import be.odisee.fastned.domain.Documentatie;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
   
   /**
    * Volgende configuratie override is nodig om de id's van Documentatie te tonen, we hebben ze nodig
    * REST geeft ze anders niet als attribuut door omdat ze ook als URI beschikbaar zijn
    */
   @Override
   public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
       config.exposeIdsFor(Documentatie.class);
   }
}