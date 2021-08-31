package org.zerock.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Member;
import org.zerock.domain.MemberRole;
import org.zerock.mapper.MemberMapper;
import org.zerock.mapper.MemberRoleMapper;
import org.zerock.service.security.UserEntity;
import org.zerock.service.security.UserRoleEntity;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;
    private final MemberRoleMapper memberRoleMapper;

    @Override
    @Transactional
    public UserEntity getUser(String loginUserId) {
        Member member = memberMapper.selectOneMemberByEmail(loginUserId);
        return new UserEntity(member.getId(), member.getName(), member.getEmail(), member.getPassword());
    }

    @Override
    @Transactional
    public List<UserRoleEntity> getUserRoles(String loginUserId) {
        List<MemberRole> memberRoles = memberRoleMapper.selectMemberRoleByEmail(loginUserId);
        List<UserRoleEntity> userRoleEntityList = new ArrayList<>();

        for(MemberRole memberRole : memberRoles) {
            userRoleEntityList.add(new UserRoleEntity(loginUserId, memberRole.getRoleName()));
        }

        return userRoleEntityList;
    }

    @Override
    @Transactional
    public Boolean addMember(Member member, boolean admin) {
        if(memberMapper.insertMember(member) <= 0) {
            return false;
        }

        long insertedMemberId = member.getId();

        MemberRole memberRole = new MemberRole();
        memberRole.setMemberId(insertedMemberId);

        if(admin) {
            memberRole.setRoleName("ROLE_ADMIN");
        } else {
            memberRole.setRoleName("ROLE_USER");
        }

        return memberRoleMapper.insertMemberRole(memberRole) > 0;
    }

    @Override
    public Boolean checkEmail(String email) {
        return memberMapper.selectUserCountByEmail(email) == 0;
    }
}
