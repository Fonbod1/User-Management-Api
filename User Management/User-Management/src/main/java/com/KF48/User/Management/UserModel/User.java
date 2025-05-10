package com.KF48.User.Management.UserModel;  // Ensure this package is correct

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")  // This ensures entities are compared by ID
@ToString(exclude = "password")  // Exclude password from toString
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_firstname")
    private String firstname;

    @Column(name = "user_secondname")
    private String secondname;

    @Column(name = "user_email")
    private String email;

    @Column(name = "password")
    private String password;

    // Use the custom Role enum for user roles
    @NotNull(message = "Role must not be null")
    @Enumerated(EnumType.STRING)  // Store enum as string in DB
    @Column(name = "user_role")
    private Role role;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
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
