package data_project.health.trainingClass.converter;

import data_project.health.trainingClass.dto.TrainingDtoRes;

import java.util.List;

public class TrainingConverter {

    public static TrainingDtoRes.bookIdRes BookId(Long book_id){
        return TrainingDtoRes.bookIdRes.builder()
                .book_id(book_id)
                .build();
    }

    public static TrainingDtoRes.searchBookList BookList(List<TrainingDtoRes.BookRes> booklist){
        return TrainingDtoRes.searchBookList.builder()
                .bookResList(booklist)
                .build();
    }
}
