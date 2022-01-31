package com.springweek2.springweek2.repository;

import com.springweek2.springweek2.model.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByPostId(Long postId);
}
