package com.springweek2.springweek2.contoller;

import com.springweek2.springweek2.dto.MemoRequestDto;
import com.springweek2.springweek2.model.Memo;
import com.springweek2.springweek2.security.UserDetailsImpl;
import com.springweek2.springweek2.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemoController {

    private final MemoService memoService;

    @Autowired
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping("/api/memos/{id}")
    public List<Memo> getMemo(@PathVariable Long id) {
        return memoService.getMemo(id);
    }

    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto memoRequestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetail) {
        String username = userDetail.getUser().getUsername();
        return memoService.createMemo(memoRequestDto, username);
    }
}
