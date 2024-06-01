package data_project.health.trainer.controller;


import data_project.health.global.response.SuccessResponse;
import data_project.health.trainer.dto.TrainerDtoReq;
import data_project.health.trainer.dto.TrainerDtoRes;
import data_project.health.trainer.service.TrainerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rentbook")
public class TrainerController {

    private final TrainerService trainerService;

    /**
     * 24.05.29 작성자 : 정주현
     * 책 대여
     */
    @PostMapping("")
    public SuccessResponse<TrainerDtoRes.RentBookRes> rentBook(@RequestBody @Valid TrainerDtoReq.rentBookReq request) {
        return SuccessResponse.success(trainerService.rentBook(request));
    }

    /**
     * 24.05.29 작성자 : 정주현
     * 책 반납
     */
    @DeleteMapping("/return/{rent_book_id}")
    public SuccessResponse<String> returnBook(@PathVariable Long rent_book_id ) {
        trainerService.returnBook(rent_book_id);
        return SuccessResponse.successWithoutResult(null);
    }

    /**
     * 24.05.29 작성자 : 정주현
     * 책 대여 연장
     */
    @PatchMapping("/renew/{rent_book_id}")
    public SuccessResponse<String> renewBook(@PathVariable Long rent_book_id ) {
        trainerService.renewBook(rent_book_id);
        return SuccessResponse.successWithoutResult(null);
    }



}
