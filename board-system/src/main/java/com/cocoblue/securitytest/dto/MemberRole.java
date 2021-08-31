package com.cocoblue.securitytest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRole {
    private long id;
    private long memberId;
    private String roleName;

    public MemberRole(long memberId, String roleName) {
        this.memberId = memberId;
        this.roleName = roleName;
    }
}
