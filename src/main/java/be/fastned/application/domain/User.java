package be.fastned.application.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name = "USERS")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true)
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private final long id;

    @Column(unique = true)
    @Size(min=1, max = 255, message="Houd de gebruikersnaam tussen 1 en 255 tekens aub!")
    private final String username;

    @Column
    @Size(min=1, max = 255, message="Houd het wachtwoord tussen 1 en 255 tekens aub!")
    private final String password;

    @Column
    @Size(min=1, max = 64, message="Houd de rol tussen 1 en 64 tekens aub!")
    private final String role;

    @Column
    @Size(min=1, max = 255, message="Houd de GSM-nummer tussen 1 en 255 tekens aub!")
    private final String beschrijving;

    @Column
    @Size(min=1, max = 255, message="Houd de status tussen 1 en 255 tekens aub!")
    private final String status;

    @Column
    @Size(min=1, max = 255, message="Houd de voornaam tussen 1 en 255 tekens aub!")
    private final String firstName;

    @Column
    @Size(min=1, max = 255, message="Houd de naam tussen 1 en 255 tekens aub!")
    private final String lastName;

    @OneToOne
    private final Persoon persoon;

    // will be used to recover dateTimeFrom for a new entry
    // so a dummy Entry must be available for each user
    @OneToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Afspraak afspraak;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
