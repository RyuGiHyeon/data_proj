package data_project.health.trainingClass.service;

import data_project.health.trainingClass.dto.TrainingClass;
import data_project.health.trainingClass.dto.TrainingClassList;
import data_project.health.trainingClass.repository.TrainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    /**
     * 24.06.01 작성자: 윤다은
     * 트레이닝 클래스 조회
     */
    public TrainingClassList getAllTrainingClasses() {
        List<TrainingClass> trainingClasses = trainingRepository.findAll();
        return new TrainingClassList(trainingClasses);
    }


}
