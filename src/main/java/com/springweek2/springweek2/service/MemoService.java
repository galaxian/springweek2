package com.springweek2.springweek2.service;

import com.springweek2.springweek2.dto.MemoRequestDto;
import com.springweek2.springweek2.model.Memo;
import com.springweek2.springweek2.repository.MemoRepository;
import com.springweek2.springweek2.security.UserDetailsImpl;
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

    public Long createMemo(MemoRequestDto memoRequestDto, String username, Long userId) {
        Memo memo = new Memo(memoRequestDto, username, userId);
        memoRepository.save(memo);
        return memo.getId();
    }

    public Long deleteMemo(Long id, UserDetailsImpl userDetails) {
        if (userDetails.getUser().getUsername().equals(memoRepository.getById(id).getUsername())) {
            memoRepository.deleteById(id);
            return id;
        }
        return (long)-1;
    }
}
