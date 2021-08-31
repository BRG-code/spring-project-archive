package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.domain.MemberRole;

import java.util.List;

@Mapper
public interface MemberRoleMapper {
    List<MemberRole> selectMemberRoleByEmail(String email);
    int insertMemberRole(MemberRole memberRole);
}
