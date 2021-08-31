package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.domain.Member;

@Mapper
public interface MemberMapper {
    Member selectOneMemberByEmail(String email);
    int selectUserCountByEmail(String email);
    int insertMember(Member member);
}
