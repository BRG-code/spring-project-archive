package com.cocoblue.securitytest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private long id;
    private String name;
    private String password;
    private String email;
    private LocalDate createDate;
    private LocalDate modifyDate;

    public Member(String email) {
        this();
        this.email = email;
    }
}