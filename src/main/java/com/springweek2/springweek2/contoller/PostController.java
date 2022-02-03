package com.springweek2.springweek2.contoller;

import com.springweek2.springweek2.dto.PostRequestDto;
import com.springweek2.springweek2.model.Post;
import com.springweek2.springweek2.model.UserRoleEnum;
import com.springweek2.springweek2.security.UserDetailsImpl;
import com.springweek2.springweek2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/api/posts")
    public List<Post> getPost() {
        return postService.getPost();
    }

    @PostMapping("/api/posts")
    public Long createPost(@RequestBody PostRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetail) {
        String username = userDetail.getUser().getUsername();

        return postService.createPost(requestDto, username);
    }

    @GetMapping("/api/posts/{id}")
    public Post selectPost(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return postService.selectPost(id);
    }

    @Secured(value = UserRoleEnum.Authority.USER)
    @PutMapping("api/posts/{id}")
    public Long updatePost(@PathVariable Long id,
                           @RequestBody PostRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.updatePost(id, requestDto, userDetails);
    }

    @DeleteMapping("api/posts/{id}")
    public Long deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(id, userDetails);
    }
}
