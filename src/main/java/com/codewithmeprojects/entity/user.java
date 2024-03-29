package com.codewithmeprojects.entity;


import jakarta.persistence.*;
import lombok.Data;
import com.codewithmeprojects.enums.userRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Data
@Table(name="users")
public class user implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;

    private String name;

    private String email;

    private String password;
    private  userRole  userRole;
    public user() {

    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));

    }

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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public com.codewithmeprojects.enums.userRole getUserRole() {
        return userRole;
    }

    public void setUserRole(userRole userRole) {
        this.userRole = userRole;
    }
}
