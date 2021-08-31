package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Borrow;
import org.zerock.service.BorrowService;
import org.zerock.service.security.CustomUserDetails;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j
public class BorrowController {
    private final BorrowService borrowService;

    @GetMapping(path = "/borrow/{bookId}")
    @PreAuthorize("isAuthenticated()")
    public Map<String, Object> borrow(@PathVariable long bookId) {
        String resultMessageKey = "message";
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("/borrow");
        Borrow borrow = new Borrow();
        log.info(bookId);
        borrow.setBookId(bookId);
        borrow.setPerson(customUserDetails.getId());
        log.info(customUserDetails.getId());
        log.info(customUserDetails.toString());
        log.info(borrow);

        Map<String, Object> result = new HashMap<>();

        if(borrowService.judgeAlreadyBorrowed(borrow)) {
            result.put(resultMessageKey, "다른 책을 대출 중입니다. 확인하고 재시도하세요.");
        } else {
            if(borrowService.insertBorrow(borrow) == 1) {
                result.put(resultMessageKey, "대출 완료");
            } else {
                result.put(resultMessageKey, "에러로 인해 대출하지 못했습니다.");
            }
        }

        log.info(result);
        return result;
    }
}
