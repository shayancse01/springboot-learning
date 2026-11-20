package com.shayancse.SecurityApp.module6SecurityApplication.entities;

import com.shayancse.SecurityApp.module6SecurityApplication.entities.enums.Role;
import com.shayancse.SecurityApp.module6SecurityApplication.utils.PermissionMapping;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Permission;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;
    private String name;


    @ElementCollection(fetch = FetchType.EAGER) //Fetching roles as soon as fetching users
    @Enumerated(EnumType.STRING) //Now the roles will be saved as Strings inside out database, otherwise it will be saved as numbers
    private Set<Role> roles; //Now one user will have multiple roles

/*
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Permission> permissions;
*/

    //The purpose of getAuthorities() is to convert our custom Role and Permission enums into SimpleGrantedAuthority objects
    // because Spring Security understands and works with GrantedAuthority objects.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*
        //Here, we are converting every role into a SimpleGrantedAuthority object.
       Set<SimpleGrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toSet());
        //So now everytime we store roles inside context holder, we will also be storing Authorities related to that user as well

        //Here, we are adding all the user's permissions to the same authorities set.
        permissions.forEach(
                permission -> authorities.add(new SimpleGrantedAuthority(permission.getName()))
        );
        return authorities; //The authorities Set will contain both the Roles and Permissions of the user.
        */

         Set<SimpleGrantedAuthority> authorities = new HashSet<>();
         roles.forEach(
                 role -> {
                     Set<SimpleGrantedAuthority> permissions = PermissionMapping.getPermissions(role);
                     authorities.addAll(permissions);
                     authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
                }
         );

        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
