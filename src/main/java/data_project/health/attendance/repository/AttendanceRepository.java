package data_project.health.attendance.repository;

import data_project.health.attendance.dto.AtdDtoReq;
import data_project.health.user.dto.UserDtoReq;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class AttendanceRepository {

    private JdbcTemplate jdbcTemplate;

    public boolean attendance(String userId){
        String query = "INSERT INTO healthManagement.Attendance(user, createdAt) VALUES (?, ?);";

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
