package com.springweek2.springweek2.service;

import com.springweek2.springweek2.dto.MemoRequestDto;
import com.springweek2.springweek2.model.Memo;
import com.springweek2.springweek2.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoService {

    private final MemoRepository memoRepository;

    @Autowired
    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public List<Memo> getMemo(Long id) {
        return memoRepository.findAllByPostId(id);
    }

    public Memo createMemo(MemoRequestDto memoRequestDto, String username) {
        Memo memo = new Memo(memoRequestDto, username);
        memoRepository.save(memo);
        return memo;
    }
}
