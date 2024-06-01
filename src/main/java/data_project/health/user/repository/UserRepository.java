package data_project.health.user.repository;

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
    public String signUp(UserDtoReq.enrollUser request){

        String phone = request.getPhone();
        String userId = phone.substring(phone.length() - 8);

        String query = "INSERT INTO User(userId, name, phone, gender, createdAt, updatedAt, status) VALUES (?, ?, ?, ?, ?, ?, '등록');";
        Object[] params = new Object[]{
                userId,
                request.getName(),
                request.getPhone(),
                request.getGender(),
                request.getCreatedAt(),
                request.getUpdatedAt()
        };

        this.jdbcTemplate.update(query, params);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, String.class);
    }

    /**
     * 24.06.01 작성자 : 류기현
     * 회원 id로 회원 기본 정보 조회
     */
    public UserDtoRes.userAttendanceA getUserBasic(String userId){

        String query = "SELECT userId, name, gender, updatedAt FROM User WHERE userId = ?";

        return this.jdbcTemplate.queryForObject(query, userAttendanceARowMapper, userId);
    }


    /**
     * 회원 id로 회원 정보 조회
     */
    public UserDtoRes.userDetails getUserDetails(String userId){

        String query = "SELECT u.name, u.gender, u.phone, u.createdAt, u.updatedAt, l.lockerId FROM User u, locker l WHERE userId = ?";

        return this.jdbcTemplate.queryForObject(query, userDetailsRowMapper, userId);
        // 트레이닝 수업, 락커번호 불러오기
    }

    private final RowMapper<UserDtoRes.userDetails> userDetailsRowMapper = (rs, rowNum) -> UserDtoRes.userDetails.builder()
            .name(rs.getString("name"))
            .gender(rs.getString("gender"))
            .phone(rs.getString("phone"))
            .createdAt(rs.getDate("createdAt"))
            .updatedAt(rs.getDate("updatedAt"))
            .locker(rs.getInt("locker"))
            .build();

    private final RowMapper<UserDtoRes.userAttendanceA> userAttendanceARowMapper = (rs, rowNum) -> UserDtoRes.userAttendanceA.builder()
            .userId(rs.getString("userId"))
            .name(rs.getString("name"))
            .gender(rs.getString("gender"))
            .updatedAt(rs.getDate("updatedAt"))
            .build();
}
