package be.fastned.application.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Collection;

public class User implements UserDetails {

    private final long id;

    private final String username;

    private final String password;

    private final String role;

    private final String beschrijving;

    private final String status;

    private final String firstName;

    private final String lastName;
}
