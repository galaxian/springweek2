package com.springweek2.springweek2.contoller;

import com.springweek2.springweek2.dto.MemoRequestDto;
import com.springweek2.springweek2.dto.PostRequestDto;
import com.springweek2.springweek2.model.Memo;
import com.springweek2.springweek2.model.UserRoleEnum;
import com.springweek2.springweek2.security.UserDetailsImpl;
import com.springweek2.springweek2.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

    @Secured(value = UserRoleEnum.Authority.USER)
    @PostMapping("/api/memos")
    public Long createMemo(@RequestBody MemoRequestDto memoRequestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetail) {
        String username = userDetail.getUser().getUsername();
        Long userId = userDetail.getUser().getId();
        return memoService.createMemo(memoRequestDto, username, userId);
    }

    @Secured(value = UserRoleEnum.Authority.USER)
    @GetMapping("api/memos/modals/{id}")
    public Memo ModalMemo(@PathVariable Long id) {
        return memoService.ModalMemo(id);
    }

    @Secured(value = UserRoleEnum.Authority.USER)
    @PutMapping("api/memos/{id}")
    public Long updateMemo(@PathVariable Long id,
                           @RequestBody MemoRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.updateMemo(id, requestDto, userDetails);
    }

    @Secured(value = UserRoleEnum.Authority.USER)
    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.deleteMemo(id, userDetails);
    }
}
