package data_project.health.user.repository;

import data_project.health.user.dto.PostLockerNumber;
import data_project.health.user.dto.PostTrainingClass;
import data_project.health.user.dto.UserDtoReq;
import data_project.health.user.dto.UserDtoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;


@Repository
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
    public UserDtoRes.userAttendanceA getUserBasic(Long userId){

        String query = "SELECT userId, name, gender, updatedAt FROM User WHERE userId = ?";

        return this.jdbcTemplate.queryForObject(query, userAttendanceARowMapper, userId);
    }


    /**
     * 회원 id로 회원 정보 조회
     * 회원 정보, 참여 수업, 출석 일자 조회
     *
     */
    public UserDtoRes.userDetails getUserDetails(Long userId){

        String query = "SELECT\n" +
                "    u.name,\n" +
                "    u.gender,\n" +
                "    u.phone,\n" +
                "    u.createdAt AS userCreatedAt,\n" +
                "    u.updatedAt AS userUpdatedAt,\n" +
                "    l.lockerId,\n" +
                "    GROUP_CONCAT(DISTINCT tc.category ORDER BY tc.category SEPARATOR ', ') AS classNames,\n" +
                "    GROUP_CONCAT(DISTINCT a.createdAt ORDER BY a.createdAt SEPARATOR ', ') AS attendanceDates\n" +
                "FROM\n" +
                "    User u\n" +
                "        JOIN\n" +
                "    locker l ON u.userId = l.user\n" +
                "        JOIN\n" +
                "    TrainingClass tc ON u.userId = tc.user\n" +
                "        JOIN\n" +
                "    Attendance a ON u.userId = a.user\n" +
                "WHERE\n" +
                "        u.userId = ?\n" +
                "GROUP BY\n" +
                "    u.userId;";

        return this.jdbcTemplate.queryForObject(query, userDetailsRowMapper, userId);
        // 트레이닝 수업, 락커번호 불러오기
        // 출석일 수도 불러오기
    }

    private final RowMapper<UserDtoRes.userDetails> userDetailsRowMapper = (rs, rowNum) -> UserDtoRes.userDetails.builder()
            .name(rs.getString("name"))
            .gender(rs.getString("gender"))
            .phone(rs.getString("phone"))
            .createdAt(rs.getDate("userCreatedAt"))
            .updatedAt(rs.getDate("userUpdatedAt"))
            .locker(rs.getInt("lockerId"))
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

        String query = "INSERT INTO ClassUser(userId, trainingClass) VALUES (?, ?);";
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

        String query = "INSERT INTO locker(lockerId, userId) VALUES (?, ?);";
        Object[] params = new Object[]{
                request.getLockerId(),
                request.getUserId()
        };
        this.jdbcTemplate.update(query, params);
        return request.getLockerId();
    }
}
