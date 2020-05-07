package be.fastned.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/login*").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/admin", "/h2/**").hasRole("ADMIN")
                .antMatchers("/fastned/**").hasRole("PLANNER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login-error")
                    .defaultSuccessUrl("/",true)
                    .permitAll()
                .and()
                .logout()
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/byebye")
                    .permitAll();
        http.exceptionHandling().accessDeniedPage("/403");
        http.csrf().disable();                                  // NEEDED FOR H2 CONSOLE
        http.headers().frameOptions().disable();                // NEEDED FOR H2 CONSOLE
    }


    /**
     * The default PasswordEncoder is now DelegatingPasswordEncoder which is a non-passive change.
     * This change ensures that passwords are now encoded using BCrypt by default
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}