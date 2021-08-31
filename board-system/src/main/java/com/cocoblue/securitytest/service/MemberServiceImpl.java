package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dao.MemberDao;
import com.cocoblue.securitytest.dao.MemberRoleDao;
import com.cocoblue.securitytest.dto.Member;
import com.cocoblue.securitytest.dto.MemberRole;
import com.cocoblue.securitytest.service.security.UserEntity;
import com.cocoblue.securitytest.service.security.UserRoleEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberDao memberDao;
    private final MemberRoleDao memberRoleDao;

    public MemberServiceImpl(MemberDao memberDao, MemberRoleDao memberRoleDao) {
        this.memberDao = memberDao;
        this.memberRoleDao = memberRoleDao;
    }

    @Override
    @Transactional
    public UserEntity getUser(String loginUserId) {
        Member member = memberDao.getMemberByEmail(loginUserId);
        return new UserEntity(member.getId(), member.getName(), member.getEmail(), member.getPassword());
    }

    @Override
    @Transactional
    public List<UserRoleEntity> getUserRoles(String loginUserId) {
        List<MemberRole> memberRoles = memberRoleDao.getRolesByEmail(loginUserId);
        List<UserRoleEntity> list = new ArrayList<>();

        for (MemberRole memberRole : memberRoles) {
            list.add(new UserRoleEntity(loginUserId, memberRole.getRoleName()));
        }
        return list;
    }

    @Override
    @Transactional
    public void addMember(Member member, boolean admin) {
        if (admin) {
            memberDao.insertAdmin(member);
        } else {
            memberDao.insertUser(member);
        }
    }

    @Override
    public Boolean checkEmail(String email) {
        return memberDao.checkEmail(email);
    }
}
