package data_project.health.trainingClass.repository;

import data_project.health.trainingClass.dto.TrainingClass;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Setter
public class TrainingRepository {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /**
     * 24.06.01 작성자: 윤다은
     * 트레이닝 수업 조회
     */
    public List<TrainingClass> findAll() {
        String query = "SELECT tc.category AS className, t.name AS trainerName, tc.schedule, tc.classTime\n" +
                "                FROM TrainingClass tc\n" +
                "                LEFT JOIN Trainer t ON tc.trainerId = t.trainerId\n" +
                "                LEFT JOIN ClassUser C on tc.trainingClassId = C.trainingClassId\n" +
                "                GROUP BY tc.category, t.name, tc.schedule, tc.classTime;";

        return this.jdbcTemplate.query(query,
                ((rs, rowNum) -> new TrainingClass(
                        rs.getString("className"),
                        rs.getString("trainerName"),
                        rs.getString("schedule"),
                        rs.getTime("classTime")
                )));
    }
}
