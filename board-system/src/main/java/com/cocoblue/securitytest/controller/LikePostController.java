package com.cocoblue.securitytest.controller;

import com.cocoblue.securitytest.dto.LikePost;
import com.cocoblue.securitytest.service.LikePostService;
import com.cocoblue.securitytest.service.PostService;
import com.cocoblue.securitytest.service.security.CustomUserDetails;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/like")
public class LikePostController {
    private final LikePostService likePostService;
    private final PostService postService;

    public LikePostController(LikePostService likePostService, PostService postService) {
        this.likePostService = likePostService;
        this.postService = postService;
    }

    @GetMapping("/do/{postId}")
    public Map<String, Object> doLike(@PathVariable String postId) {
        Map<String, Object> result = new HashMap<>();

        // 비 로그인 상태라면, 401 에러를 표출
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            throw new AccessDeniedException("");
        }

        // PathVariable 검증
        if(postService.getPost(postId) == null) {
            throw new NotFoundException("");
        }

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LikePost likePost = new LikePost();
        likePost.setPostId(Long.parseLong(postId));
        likePost.setMemberId(customUserDetails.getId());

        // 이미 추천했는가 검증
        if(likePostService.judgeAlreadyLike(Long.parseLong(postId), customUserDetails.getId())) {
            result.put("result", false);
            result.put("exist", true);
            System.out.println(result);
            return result;
        }

        if(likePostService.insertLikePost(likePost)) {
            result.put("result", true);
        } else {
            result.put("result", false);
        }

        System.out.println(result);
        return result;
    }

    @GetMapping("/get/{postId}")
    public Map<String, Object> getLike(@PathVariable String postId) {
        Map<String, Object> result = new HashMap<>();

        // PathVariable 검증
        if(postService.getPost(postId) == null) {
            throw new NotFoundException("");
        }

        long count = likePostService.getLikeCount(Long.parseLong(postId));
        result.put("result", true);
        result.put("count", count);

        return result;
    }
}
