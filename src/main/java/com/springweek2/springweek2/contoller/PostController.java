package com.springweek2.springweek2.contoller;

import com.springweek2.springweek2.dto.PostRequestDto;
import com.springweek2.springweek2.model.Post;
import com.springweek2.springweek2.repository.PostRepository;
import com.springweek2.springweek2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public Post createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    @GetMapping("/api/posts/{id}")
    public Post selectPost(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return postService.selectPost(id);
    }
}
