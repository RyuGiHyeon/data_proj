package data_project.health.trainingClass.controller;

import data_project.health.trainingClass.dto.TrainingDtoReq;
import data_project.health.trainingClass.dto.TrainingDtoRes;
import data_project.health.trainingClass.service.TrainingService;
import data_project.health.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trainingClass")
public class TrainingController {

    private final TrainingService trainingService;

    /**
     * 25.06.01 작성자 : 류기현
     * TrainingClass 등록
     */
    @PostMapping("/enroll")
    public SuccessResponse<TrainingDtoRes.bookIdRes> bookEnroll(@RequestBody @Valid TrainingDtoReq.bookEnroll request){
        return SuccessResponse.success(trainingService.bookEnroll(request));
    }
    /**
     * 25.05.29 작성자 : 정주현
     * 책 수정
     */

    @PatchMapping("/update")
    public SuccessResponse<String> bookUpdate(@RequestBody @Valid TrainingDtoReq.bookUpdate request){
        trainingService.bookUpdate(request);
        return SuccessResponse.successWithoutResult(null);
    }
    /**
     * 25.05.29 작성자 : 정주현
     * 책 삭제
     */

    @DeleteMapping("/delete")
    public SuccessResponse<String> bookDelete(@RequestParam(name = "book_id")Long book_id){
        trainingService.bookDelete(book_id);
        return SuccessResponse.successWithoutResult(null);
    }
    /**
     * 25.05.29 작성자 : 정주현
     * 책 목록 조회
     */

    @GetMapping("/search")
    public SuccessResponse<TrainingDtoRes.searchBookList> bookSearch(){
        TrainingDtoRes.searchBookList list = trainingService.bookSearch();
        return SuccessResponse.success(list);
    }


    /**
     * 25.05.29 작성자 : 정주현
     * 대여 가능 책 목록 조회
     */
    @GetMapping("/search/rent")
    public SuccessResponse<TrainingDtoRes.searchBookList> bookRentSearch(){
        TrainingDtoRes.searchBookList list = trainingService.bookRentSearch();
        return SuccessResponse.success(list);
    }

}
