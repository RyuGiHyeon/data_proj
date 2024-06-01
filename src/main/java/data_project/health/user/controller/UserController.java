package data_project.health.user.controller;

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
     * 24.05.31 작성자 : 류기현
     * 회원 등록
     */
    @PostMapping("/signup")
    public SuccessResponse<UserDtoRes.enrollUser> signUp(@RequestBody @Valid UserDtoReq.enrollUser request) {
        return SuccessResponse.success(userService.signUp(request));
    }

    /**
     * 24.05.31 작성자 : 류기현
     * 회원 출석
     */
    @PostMapping("/attendance")
    public SuccessResponse<UserDtoRes.userAttendanceB> attendance(@RequestBody @Valid UserDtoReq.attendance request){
        return SuccessResponse.success(userService.attendance(request));
    }


    /**
     * 24.06.01 작성자: 윤다은
     * 회원 조회
     */
    @GetMapping("/userDetails")
    public SuccessResponse<UserDtoRes.userDetails> userDetails(@RequestBody @Valid UserDtoReq.userByUserId request){
        return SuccessResponse.success(userService.details(request));
    }


    /**
     * 24.05.31 작성자 : 류기현
     * 회원 트레이닝 수업 등록
     */


    /**
     * 24.05.31 작성자 : 류기현
     * 회원 락커 등록
     */


    /**
     * 24.05.31 작성자 : 류기현
     * 회원 탈퇴
     */
}
