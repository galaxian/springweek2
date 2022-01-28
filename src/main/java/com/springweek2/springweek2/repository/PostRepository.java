package com.springweek2.springweek2.repository;

import com.springweek2.springweek2.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
}
