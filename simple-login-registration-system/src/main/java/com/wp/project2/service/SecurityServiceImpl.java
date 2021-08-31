package com.wp.project2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
// 보안 서비스 Impl
public class SecurityServiceImpl implements SecurityService{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Override
    // 현재 로그인 중인 계정을 찾아내는 메소드
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }

        return null;
    }

    @Override
    // 회원가입 한 아이디로 자동으로 로그인하는 메소드
    // 코드 진행 이해 참고: https://flyburi.com/584
    public void autologin(String username, String password) {
        // 유저의 상세 정보를 받아옴
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // 정보를 검증하기 위해 객체 정의
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        // 정보를 검증
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // 정보가 검증이 되었다면
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug(String.format("%s님으로 자동 로그인 되었습니다.", username));
        }
    }
}
