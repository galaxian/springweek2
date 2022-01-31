package com.springweek2.springweek2.model;

import com.springweek2.springweek2.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Memo extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long userId;

    public Memo(MemoRequestDto memoRequestDto) {
        this.postId = memoRequestDto.getPostId();
        this.contents = memoRequestDto.getContents();
    }

    public Memo(MemoRequestDto memoRequestDto, String username, Long userId) {
        this.postId = memoRequestDto.getPostId();
        this.username = username;
        this.contents = memoRequestDto.getContents();
        this.userId = userId;
    }
}
