package data_project.health.trainingClass.repository;

import data_project.health.trainingClass.dto.TrainingClass;
import data_project.health.trainingClass.dto.TrainingDtoReq;
import data_project.health.trainingClass.dto.TrainingDtoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class TrainingRepository {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /**
     * 24.06.01 작성자: 윤다은
     * 트레이닝 수업 조회
     */
    public List<TrainingClass> findAll() {
        String query = "SELECT tc.category AS className, t.name AS trainerName, tc.schedule, tc.classTime, COUNT(cu.user) AS studentCount\n" +
                "FROM TrainingClass tc\n" +
                "JOIN Trainer t ON tc.trainer = t.trainerId\n" +
                "JOIN ClassUser cu ON tc.ptId = cu.trainingClass\n" +
                "GROUP BY tc.category, t.name, tc.schedule, tc.classTime;";

        return this.jdbcTemplate.query(query,
                ((rs, rowNum) -> new TrainingClass(
                        rs.getString("className"),
                        rs.getString("trainerName"),
                        rs.getString("schedule"),
                        rs.getTime("classTime"),
                        rs.getInt("studentCount")
                )));
    }

    /*private final RowMapper<TrainingClass> trainingClassRowMapper = (rs, rowNum) -> {
        TrainingClass trainingClass = new TrainingClass();
        trainingClass.setClassName(rs.getString("className"));
        trainingClass.setTrainerName(rs.getString("trainerName"));
        trainingClass.setSchedule(rs.getString("schedule"));
        trainingClass.setClassTime(rs.getTimestamp("classTime"));
        trainingClass.setStudentCount(rs.getInt("studentCount"));
        return trainingClass;
    };*/

}
