package org.zerock.service;

import org.zerock.domain.Member;
import org.zerock.service.security.UserDbService;

public interface MemberService extends UserDbService {
    Boolean addMember(Member member, boolean b);
    Boolean checkEmail(String email);
}
