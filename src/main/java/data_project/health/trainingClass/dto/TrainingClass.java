package data_project.health.trainingClass.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingClass implements Serializable {
    private String className;  // 트레이닝 클래스 카테고리
    private String trainerName; //트레이너 이름 join해서 가져오기
    private String schedule;
    private Date classTime;
    private int studentCount; //학생 수 count

}
