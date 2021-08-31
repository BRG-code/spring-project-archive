package com.cocoblue.securitytest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikePost {
    private long id;
    private long postId;
    private long memberId;
    private LocalDateTime likeTime;
}
