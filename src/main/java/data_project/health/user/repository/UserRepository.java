package data_project.health.user.repository;

import data_project.health.user.dto.PostLockerNumber;
import data_project.health.user.dto.PostTrainingClass;
import data_project.health.user.dto.UserDtoReq;
import data_project.health.user.dto.UserDtoRes;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;


@Repository
@Setter
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    // 전화번호 중복 확인

    /**
     * 24.06.01 작성자 : 류기현
     * 전화번호 중복 확인
     */
    public boolean dupPhone(String phone) {
        String query = "SELECT COUNT(*) FROM User U WHERE U.phone = ?";

        Integer count = this.jdbcTemplate.queryForObject(query, Integer.class, phone);
        // Optional을 사용하여 NullPointerException 방지
        return Optional.ofNullable(count).orElse(0) > 0;
    }

    /**
     * 24.06.01 작성자 : 류기현
     * 회원 가입
     */
    public Long signUp(UserDtoReq.enrollUser request){

        String query = "INSERT INTO User(name, phone, gender, createdAt, updatedAt, status) VALUES (?, ?, ?, ?, ?, '등록');";
        Object[] params = new Object[]{
                request.getName(),
                request.getPhone(),
                request.getGender(),
                request.getCreatedAt(),
                request.getUpdatedAt()
        };

        this.jdbcTemplate.update(query, params);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, Long.class);
    }

    /**
     * 24.06.01 작성자 : 류기현
     * 회원 id로 회원 기본 정보 조회
     */
    public UserDtoRes.userAttendanceA getUserBasic(String phone){

        String query = "SELECT userId, name, gender, updatedAt FROM User WHERE phone = ?";

        return this.jdbcTemplate.queryForObject(query, userAttendanceARowMapper, phone);
    }


    /**
     * 회원 phone 으로 회원 정보 조회
     * 회원 정보, 참여 수업, 출석 일자 조회
     *
     */
    public UserDtoRes.userDetails getUserDetails(String phone) {
        String query = """
                SELECT
                    u.name,
                    u.gender,
                    u.phone,
                    u.createdAt AS userCreatedAt,
                    u.updatedAt AS userUpdatedAt,
                    l.lockerId,
                    GROUP_CONCAT(DISTINCT tc.category ORDER BY tc.category SEPARATOR ', ') AS classNames,
                    GROUP_CONCAT(DISTINCT a.createdAt ORDER BY a.createdAt SEPARATOR ', ') AS attendanceDates
                FROM
                    User u
                LEFT JOIN
                    Locker l ON u.userId = l.userId
                LEFT JOIN
                    Attendance a ON u.userId = a.userId
                LEFT JOIN
                    ClassUser cu ON u.userId = cu.userId
                LEFT JOIN
                    TrainingClass tc ON tc.trainingClassId = cu.trainingClassId
                WHERE
                    u.phone = ?
                GROUP BY
                    u.userId
                ORDER BY
                    u.userId;
                """;

        List<UserDtoRes.userDetails> results = this.jdbcTemplate.query(query, userDetailsRowMapper, phone);

        if (results.isEmpty()) {
            return UserDtoRes.userDetails.builder()
                    .name(null)
                    .gender(null)
                    .phone(phone)
                    .createdAt(null)
                    .updatedAt(null)
                    .locker(0)
                    .classNames("non")
                    .attendanceDates("non")
                    .build();
        } else {
            UserDtoRes.userDetails userDetails = results.get(0);
            userDetails.setLocker(userDetails.getLocker() == null ? 0 : userDetails.getLocker());
            userDetails.setClassNames(userDetails.getClassNames() == null ? "non" : userDetails.getClassNames());
            userDetails.setAttendanceDates(userDetails.getAttendanceDates() == null ? "non" : userDetails.getAttendanceDates());
            return userDetails;
        }
    }

    private final RowMapper<UserDtoRes.userDetails> userDetailsRowMapper = (rs, rowNum) -> UserDtoRes.userDetails.builder()
            .name(rs.getString("name"))
            .gender(rs.getString("gender"))
            .phone(rs.getString("phone"))
            .createdAt(rs.getDate("userCreatedAt"))
            .updatedAt(rs.getDate("userUpdatedAt"))
            .locker(rs.getObject("lockerId") != null ? rs.getInt("lockerId") : null)
            .classNames(rs.getString("classNames"))
            .attendanceDates(rs.getString("attendanceDates"))
            .build();

    private final RowMapper<UserDtoRes.userAttendanceA> userAttendanceARowMapper = (rs, rowNum) -> UserDtoRes.userAttendanceA.builder()
            .userId(rs.getLong("userId"))
            .name(rs.getString("name"))
            .gender(rs.getString("gender"))
            .updatedAt(rs.getDate("updatedAt"))
            .build();



    /**
     * 24.06.01 작성자 : 윤다은
     * 수강 등록
     */
    public Long postTrainingClass(PostTrainingClass request) {

        String query = "INSERT INTO ClassUser(userId, trainingClassId) VALUES (?, ?);";
        Object[] params = new Object[]{

                request.getUserId(),
                request.getClassId()

        };
        this.jdbcTemplate.update(query, params);

        //String lastInsertIdQuery = "select last_insert_id()";
        //return this.jdbcTemplate.queryForObject(lastInsertIdQuery,String.class);

        return request.getUserId();

    }


    /**
     * 24.06.01 작성자 : 윤다은
     * Locker 등록
     */
    public Long postLockerNumber(PostLockerNumber request) {

        String query = "UPDATE Locker SET userId = ?, status = 'use' WHERE lockerId = ?";
        Object[] params = new Object[]{
                request.getUserId(),
                request.getLockerId()
        };
        this.jdbcTemplate.update(query, params);
        return request.getLockerId();
    }
}
