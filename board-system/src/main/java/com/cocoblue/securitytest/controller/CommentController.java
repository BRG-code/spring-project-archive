package com.cocoblue.securitytest.controller;

import com.cocoblue.securitytest.dto.Comment;
import com.cocoblue.securitytest.service.CommentService;
import com.cocoblue.securitytest.service.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("insert/{postId}")
    public String insertComment(@ModelAttribute Comment comment,
                                @PathVariable(name = "postId") long postId,
                                Model model) {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            model.addAttribute("status", "login");
            return "posts/commentstatus";
        }

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        comment.setWriterId(customUserDetails.getId());
        comment.setPostId(postId);

        if(commentService.writeComment(comment)) {
            model.addAttribute("status", "success");
        } else {
            model.addAttribute("status", "server");
        }
        return "posts/commentstatus";
    }

    @RequestMapping("delete/{commentId}")
    public String deleteComment(Model model, @PathVariable(name = "commentId") long commentId) {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            // 비로그인 상태에서 접근한 경우
            model.addAttribute("result", "No Login Value");
        }

        Comment comment = commentService.getComment(Long.toString(commentId));
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(customUserDetails.getId() == comment.getWriterId()) {
            if(commentService.deleteComment(Long.toString(commentId))) {
                // 댓글 삭제가 완료되면.
                model.addAttribute("result", "Success");
            } else {
                model.addAttribute("result", "Fail");
            }
        } else {
            // 로그인 된 ID와 댓글 작성자의 ID가 일치하지 않는 경우
            model.addAttribute("result", "Permission Error");
        }

        return "comments/deleteresult";
    }
}
