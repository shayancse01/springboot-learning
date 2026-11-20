package com.shayancse.SecurityApp.module6SecurityApplication.utils;

import com.shayancse.SecurityApp.module6SecurityApplication.entities.enums.Permission;
import com.shayancse.SecurityApp.module6SecurityApplication.entities.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.shayancse.SecurityApp.module6SecurityApplication.entities.enums.Permission.*;
import static com.shayancse.SecurityApp.module6SecurityApplication.entities.enums.Role.*;

public class PermissionMapping {

    private static final Map<Role, Set<Permission>> map = Map.of(
            USER, Set.of(USER_VIEW, POST_VIEW),
            CREATOR, Set.of(POST_CREATE, USER_UPDATE, POST_UPDATE),
            ADMIN, Set.of(POST_CREATE, USER_UPDATE, POST_UPDATE, USER_DELETE, USER_CREATE, POST_DELETE)
    );

    //fetching the permissions associated with a particular role and
    //converting them into SimpleGrantedAuthority objects, which Spring Security understands.
    public static Set<SimpleGrantedAuthority> getPermissions(Role role) {
        return map.get(role).stream()  //It takes a Role as an argument and retrieves the Set of Permissions associated with that particular role.
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }


}
