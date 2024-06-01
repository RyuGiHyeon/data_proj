package data_project.health.trainingClass.repository;

import data_project.health.trainingClass.dto.TrainingDtoReq;
import data_project.health.trainingClass.dto.TrainingDtoRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
     * sellerDao - 2
     * 23.06.29 작성자 : 정주현
     * 회원가입 INSERT 쿼리
     */
    public Long bookEnroll(TrainingDtoReq.bookEnroll request){
        String query = "INSERT INTO Book(book_name, book_author) VALUES (?, ?);";
        Object[] params = new Object[]{
                request.getBook_name(),
                request.getBook_author(),
        };

        this.jdbcTemplate.update(query, params);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,Long.class);
    }


    /**
     * sellerDao - 2
     * 23.06.29 작성자 : 정주현
     * Book 정보 수정
     */

    public int bookUpdate(TrainingDtoReq.bookUpdate request) {
        String updateQuery = "UPDATE Book SET " +
                "book_name = ? ,book_author = ? " +
                "WHERE book_id = ? ";
        return jdbcTemplate.update(updateQuery, request.getBook_name(),request.getBook_author(), request.getBook_id());
    }


    public int bookDelte(Long book_id) {
        String query = "DELETE FROM Book WHERE book_id = ?";
        return jdbcTemplate.update(query,book_id);

    }

    /**
     * 24.05.29 작성자 : 정주현
     * Book 목록 조회
     */
    public List<TrainingDtoRes.BookRes> searchBook() {
        String query = "SELECT * FROM Book ";

        return this.jdbcTemplate.query(query,
                (rs, rowNum) -> new TrainingDtoRes.BookRes(
                        rs.getLong("book_id"),
                        rs.getString("book_name"),
                        rs.getString("book_author"),
                        rs.getString("status")
                ));
    }

    public List<TrainingDtoRes.BookRes> searchRentBook() {
        String query = "SELECT * FROM Book WHERE status = 'AVAILABLE'";

        return this.jdbcTemplate.query(query,
                (rs, rowNum) -> new TrainingDtoRes.BookRes(
                        rs.getLong("book_id"),
                        rs.getString("book_name"),
                        rs.getString("book_author"),
                        rs.getString("status")
                ));
    }

}
