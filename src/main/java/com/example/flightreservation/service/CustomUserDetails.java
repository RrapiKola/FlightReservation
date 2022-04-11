package com.example.flightreservation.service;

import com.example.flightreservation.entity.Role;
import com.example.flightreservation.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails (User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roleSet = user.getRoleSet();
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
        for (Role role : roleSet) {
            simpleGrantedAuthorityList.add( new SimpleGrantedAuthority(role.getRole()));
        }
        return simpleGrantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
