package com.cocoblue.securitytest.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserDbService userdbService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity customUser = userdbService.getUser(username);
        if (customUser.getPassword() == null) {
            throw new UsernameNotFoundException("사용자가 입력한 아이디에 해당하는 사용자를 찾을 수 없습니다.");
        }

        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setId(customUser.getId());
        customUserDetails.setName(customUser.getName());
        customUserDetails.setEmail(customUser.getLoginUserId());
        customUserDetails.setPassword(customUser.getPassword());

        List<UserRoleEntity> customRoles = userdbService.getUserRoles(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (customRoles != null) {
            for (UserRoleEntity customRole : customRoles) {
                authorities.add(new SimpleGrantedAuthority(customRole.getRoleName()));
            }
        }

        customUserDetails.setAuthorities(authorities);
        customUserDetails.setEnabled(true);
        customUserDetails.setAccountNonExpired(true);
        customUserDetails.setAccountNonLocked(true);
        customUserDetails.setCredentialsNonExpired(true);
        return customUserDetails;
    }
}