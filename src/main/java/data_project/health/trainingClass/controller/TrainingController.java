package data_project.health.trainingClass.controller;

import data_project.health.trainingClass.dto.TrainingClassList;
import data_project.health.trainingClass.service.TrainingService;
import data_project.health.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trainingClass")
public class TrainingController {

    private final TrainingService trainingService;

    /**
     * 25.06.01 작성자 : 윤다은
     * TrainingClass 모든 목록 보기
     */
    @GetMapping("/all")
    public SuccessResponse<TrainingClassList> getAllTrainingClasses() {
        return SuccessResponse.success(trainingService.getAllTrainingClasses());
    }

}
