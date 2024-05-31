package data_project.health.user.controller;

import data_project.health.book.dto.BookDtoRes;
import data_project.health.global.response.SuccessResponse;
import data_project.health.user.dto.UserDtoReq;
import data_project.health.user.dto.UserDtoRes;
import data_project.health.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    /**
     * 24.01.19 작성자 : 정주현
     * 회원가입
     */
    @PostMapping("/signup")
    public SuccessResponse<UserDtoRes.enrollUser> signUp(@RequestBody @Valid UserDtoReq.enrollUser request) {
        return SuccessResponse.success(userService.signUp(request));
    }

    /**
     * 24.01.19 작성자 : 정주현
     * 대여한 책 조회
     */
    @GetMapping("/serach/rentbook")
    public SuccessResponse<BookDtoRes.searchMyBookList> rentBookSearch(@RequestParam(name = "user_id") Long user_id) {

        return SuccessResponse.success(userService.rentBookSearch(user_id));
    }
}
