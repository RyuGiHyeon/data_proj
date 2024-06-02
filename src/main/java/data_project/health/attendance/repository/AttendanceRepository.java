package data_project.health.attendance.repository;

import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Setter
public class AttendanceRepository {

    private JdbcTemplate jdbcTemplate;

    public boolean attendance(Long userId){
        String query = "INSERT INTO healthManagement.Attendance(userId, createdAt) VALUES (?, ?);";

        // 현재 날짜와 시간을 생성
        Date now = new Date();

        Object[] params = new Object[]{
                userId,
                now
        };

        int rowsAffected = this.jdbcTemplate.update(query, params);

        return rowsAffected > 0;
        }
}
