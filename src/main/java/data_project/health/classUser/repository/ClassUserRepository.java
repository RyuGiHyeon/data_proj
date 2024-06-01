package data_project.health.classUser.repository;

import data_project.health.user.dto.UserDtoReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ClassUserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String enrollClass(UserDtoReq.enrollClass request){

        String query = "INSERT INTO ClassUser(userId, trainingClass) VALUES (?, ?);";
        Object[] params = new Object[]{
                request.getUserId(),
                request.getTrainingClass()
        };

        this.jdbcTemplate.update(query, params);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, String.class);
    }
}
