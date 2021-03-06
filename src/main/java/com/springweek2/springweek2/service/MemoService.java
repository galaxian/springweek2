package com.springweek2.springweek2.service;

import com.springweek2.springweek2.dto.MemoRequestDto;
import com.springweek2.springweek2.model.Memo;
import com.springweek2.springweek2.repository.MemoRepository;
import com.springweek2.springweek2.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MemoService {

    private final MemoRepository memoRepository;

    @Autowired
    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public List<Memo> getMemo(Long id) {
        return memoRepository.findAllByPostIdOrderByModifiedAtDesc(id);
    }

    public Long createMemo(MemoRequestDto memoRequestDto, String username, Long userId) {
        if(memoRequestDto.getContents().length() == 0)
            return 0L;
        Memo memo = new Memo(memoRequestDto, username, userId);
        memoRepository.save(memo);
        return memo.getId();
    }

    public Long deleteMemo(Long id, UserDetailsImpl userDetails) {
        if (userDetails.getUser().getUsername().equals(memoRepository.getById(id).getUsername())) {
            memoRepository.deleteById(id);
            return id;
        }
        return -1L;
    }

    @Transactional
    public Long updateMemo(Long id, MemoRequestDto requestDto, UserDetailsImpl userDetails) {
        if (userDetails.getUser().getUsername().equals(memoRepository.getById(id).getUsername())) {
            if(requestDto.getContents().length() == 0)
                return 0L;
            Memo memo = memoRepository.findById(id).orElseThrow(
                    () -> new NullPointerException("선택한 댓글이 존재하지 않습니다.")
            );
            String contents = requestDto.getContents();
            memo.setContents(contents);
            memoRepository.save(memo);
            return id;
        }
        return -1L;
    }

    public Memo ModalMemo(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("찾는 댓글이 존재하지 않습니다.")
        );
        return memo;
    }
}
