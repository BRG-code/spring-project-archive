package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dto.Member;
import com.cocoblue.securitytest.service.security.UserDbService;

public interface MemberService extends UserDbService {
    void addMember(Member member, boolean b);
    Boolean checkEmail(String email);
}
