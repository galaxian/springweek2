package com.springweek2.springweek2.service;

import com.springweek2.springweek2.dto.PostRequestDto;
import com.springweek2.springweek2.dto.SignupRequestDto;
import com.springweek2.springweek2.model.Post;
import com.springweek2.springweek2.repository.PostRepository;
import com.springweek2.springweek2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPost() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    public Post createPost(PostRequestDto requestDto, String username) {
        Post post = new Post(requestDto, username);
        postRepository.save(post);
        return post;
    }

    public Post selectPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("선택한 게시글이 존재하지 않습니다.")
        );
    }

    public Long deletePost(Long id) {
        postRepository.deleteById(id);
        return id;
    }
}
