package com.springweek2.springweek2.service;

import com.springweek2.springweek2.dto.PostRequestDto;
import com.springweek2.springweek2.model.Post;
import com.springweek2.springweek2.repository.PostRepository;
import com.springweek2.springweek2.repository.UserRepository;
import com.springweek2.springweek2.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPost() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    public Long createPost(PostRequestDto requestDto, String username) {
        if(requestDto.getContents().length() == 0 || requestDto.getTitle().length() == 0)
            return 0L;
        Post post = new Post(requestDto, username);
        postRepository.save(post);
        return post.getId();
    }

    public Post selectPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("선택한 게시글이 존재하지 않습니다.")
        );
    }

    @Transactional
    public Long updatePost(Long id, PostRequestDto requestDto, UserDetailsImpl userDetails) {
        if (userDetails.getUser().getUsername().equals(postRepository.getById(id).getUsername())) {
            if(requestDto.getContents().length() == 0 || requestDto.getTitle().length() == 0)
                return 0L;
            Post post = postRepository.findById(id).orElseThrow(
                    () -> new NullPointerException("선택한 게시글이 존재하지 않습니다.")
            );
            String contents = requestDto.getContents();
            String title = requestDto.getTitle();
            post.setContents(contents);
            post.setTitle(title);
            postRepository.save(post);
            return id;
        }
        return -1L;
    }

    public Long deletePost(Long id, UserDetailsImpl userDetails) {
        if (userDetails.getUser().getUsername().equals(postRepository.getById(id).getUsername())) {
            postRepository.deleteById(id);
            return id;
        }
        return -1L;
    }
}
