package com.nbenliogludev.userauthentication.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author nbenliogludev
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(name = "name", length = 80, nullable = false)
    @Size(min = 2, max = 80, message = "Name must be between 2 and 80 characters")
    private String firstname;

    @NotNull(message = "Surname is required")
    @Column(name = "surname", length = 80, nullable = false)
    @Size(min = 2, max = 80, message = "Surname must be between 2 and 80 characters")
    private String lastname;

    @NotBlank(message = "Email is required")
    @Column(name = "email", length = 100, nullable = false, unique = true)
    @Email(message = "Email is not valid")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 2, max = 80, message = "Password must be between 2 and 80 characters")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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