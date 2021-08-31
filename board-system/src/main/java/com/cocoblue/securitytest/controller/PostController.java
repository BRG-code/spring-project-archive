package com.cocoblue.securitytest.controller;

import com.cocoblue.securitytest.dto.Comment;
import com.cocoblue.securitytest.dto.Post;
import com.cocoblue.securitytest.service.CommentService;
import com.cocoblue.securitytest.service.LikePostService;
import com.cocoblue.securitytest.service.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.cocoblue.securitytest.service.PostService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(path = "/board")
public class PostController {
    private final PostService postService;
    private final CommentService commentService;
    private final LikePostService likePostService;

    public PostController(PostService postService, CommentService commentService,
                          LikePostService likePostService) {
        this.postService = postService;
        this.commentService = commentService;
        this.likePostService = likePostService;
    }

    @RequestMapping("/posts")
    public String getPostList(Model model, @RequestParam(name = "page", required = false) String page,
                                            @RequestParam(name = "keyword", required = false) String keyword) {

        // requestparam이 없을 경우 기본 값 정의
        if(page == null) {
            page = "1";
        }

        List<Post> posts;

        if(keyword != null) {
            model.addAttribute("searchKeyword", keyword);
            posts = postService.getPostsByKeyword("자유 게시판", keyword, Integer.parseInt(page) - 1);
            model.addAttribute("pagesCount", getTotalPage(postService.getPostsCountByKeyword("자유 게시판", keyword)));
            model.addAttribute("searchStatus", "Success");

            // 검색 결과가 없을 경우, 1페이지를 갖고 옴.
            if(posts.size() == 0) {
                model.addAttribute("searchStatus", "Fail");

                posts = postService.getPostsByPage("자유 게시판", Integer.parseInt("0"));
                model.addAttribute("pagesCount", getTotalPage(postService.getPostsCount("자유 게시판")));
            }
        } else {
            posts = postService.getPostsByPage("자유 게시판", Integer.parseInt(page) - 1);

            // 해당 페이지에 글이 없을 경우 1페이지를 가져옴.
            if(posts.size() == 0) {
                posts = postService.getPostsByPage("자유 게시판", Integer.parseInt("0"));
            }

            model.addAttribute("pagesCount", getTotalPage(postService.getPostsCount("자유 게시판")));
        }

        model.addAttribute("posts", posts);
        System.out.println(posts);
        model.addAttribute("nowPage", Integer.parseInt(page));

        addLoginImf(model);

        return "posts/posts";
    }

    @GetMapping("read/{id}")
    public String getPost(@PathVariable String id, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        long commentCount = commentService.getCommentCount(id);
        model.addAttribute("comment_count", commentCount);
        model.addAttribute("postId", id);
        model.addAttribute("likeCount", likePostService.getLikeCount(Long.parseLong(id)));

        // 조회수 증가
        postService.increaseViewNum(id);

        if(commentCount != 0) {
            List<Comment> comments = commentService.getComments(id);
            model.addAttribute("comments", comments);
        }

        addLoginImf(model);

        return "posts/read";
    }

    @RequestMapping("write")
    public String getWrite(Model model, @RequestParam(name="mode", required = false) String mode,
                           @RequestParam(name = "postId", required = false) String postId) {
        if(mode != null) {
            if(!mode.equals("modify")) {
                return "posts/write";
            }

            if(postId == null) {
                return "posts/write";
            }

            Post post = postService.getPost(postId);
            model.addAttribute("post", post);

            CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(customUserDetails.getId() != post.getWriterId()) {
                return "posts/write";
            }

            model.addAttribute("mode", "modify");
            model.addAttribute("postId", postId);
        }

        return "posts/write";
    }

    @PostMapping("insertpost")
    public String writePost(@ModelAttribute Post post) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setWriterId(customUserDetails.getId());
        post.setBoardId(1);
        post.setViewNumber(0);
        post.setWriteTime(LocalDateTime.now());

        postService.writePost(post);
        return "redirect:/board/posts";
    }

    @RequestMapping("delete/{postId}")
    public String deletePost(Model model, @PathVariable(name = "postId") String postId) {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            // 비로그인 상태에서 접근한 경우
            model.addAttribute("result", "No Login Value");
        }

        Post post = postService.getPost(postId);
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(customUserDetails.getId() == post.getWriterId()) {
            if(postService.deletePost(postId)) {
                // 게시글 삭제가 완료되면.
                model.addAttribute("result", "Success");
            } else {
                model.addAttribute("result", "Fail");
            }
        } else {
            // 로그인 된 ID와 게시글 작성자의 ID가 일치하지 않는 경우
            model.addAttribute("result", "Permission Error");
        }

        return "posts/deleteresult";
    }

    @RequestMapping("modifypost")
    public String updatePost(@ModelAttribute Post post, Model model, @RequestParam(name = "postId") String postId) {
        post.setId(Long.parseLong(postId));

        if(postService.updatePost(post)) {
            // 게시글 변경이 완료되면.
            model.addAttribute("result", "Success");
            model.addAttribute("postId", post.getId());
        } else {
            model.addAttribute("result", "Fail");
        }

        return "posts/updateresult";
    }

    private Model addLoginImf(Model model) {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            return model;
        }

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("loginedId", customUserDetails.getId());
        model.addAttribute("loginedName", customUserDetails.getName());

        return model;
    }

    private long getTotalPage(long postsCount) {
        if(postsCount % 4 == 0) {
            return postsCount / 4;
        } else {
            return postsCount / 4 + 1;
        }
    }
}
