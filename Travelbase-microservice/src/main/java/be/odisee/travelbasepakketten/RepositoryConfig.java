package be.odisee.travelbasepakketten;

import be.odisee.travelbasepakketten.domain.Pakket;
import be.odisee.travelbasepakketten.domain.PakketActiviteit;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Pakket.class, PakketActiviteit.class);
    }
}
