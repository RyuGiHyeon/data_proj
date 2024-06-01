package data_project.health.user.repository;

import data_project.health.locker.dto.BookDtoRes;
import data_project.health.user.dto.UserDtoReq;
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


    /**
     * sellerDao - 2
     * 23.06.29 작성자 : 김성인
     * 회원가입 INSERT 쿼리
     */
//    public Long attendent(UserDtoReq.enrollUserReq request){
//        String query = "INSERT INTO user(email, name, password) VALUES (?, ?, ?);";
//        Object[] params = new Object[]{
//                request.getEmail(),
//                request.getName(),
//                request.getPassword()
//        };
//
//        this.jdbcTemplate.update(query, params);
//
//        String lastInsertIdQuery = "select last_insert_id()";
//        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,Long.class);
//    }

    // 전화번호 중복 확인 - 미완
    public boolean dupPhone(String phone) {
        String query = "SELECT COUNT(*) FROM User U WHERE U.phone = ?";

        Integer count = this.jdbcTemplate.queryForObject(query, Integer.class, phone);
        // Optional을 사용하여 NullPointerException 방지
        return Optional.ofNullable(count).orElse(0) > 0;
    }

    public Long signUp(UserDtoReq.enrollUser request){

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
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, Long.class);
    }
}
