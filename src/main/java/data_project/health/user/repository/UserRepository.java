package data_project.health.user.repository;

import data_project.health.locker.dto.BookDtoRes;
import data_project.health.user.dto.UserDtoReq;
import data_project.health.user.dto.UserDtoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
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
     * 회원 id로 정보 찾기
     */
    public UserDtoRes.userDetails getUserInfoByUserId(String userId){

        String query = "SELECT name, gender, updatedAt from User WHERE userId = ?;";
        Object[] params = new Object[]{
                userId
        };

        this.jdbcTemplate.update(query, params);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, UserDtoRes.userDetails.class);
    }
}
