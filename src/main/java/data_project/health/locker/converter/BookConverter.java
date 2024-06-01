package data_project.health.locker.converter;

import data_project.health.locker.dto.BookDtoRes;

import java.util.List;

public class BookConverter {

    public static BookDtoRes.bookIdRes BookId(Long book_id){
        return BookDtoRes.bookIdRes.builder()
                .book_id(book_id)
                .build();
    }

    public static BookDtoRes.searchBookList BookList(List<BookDtoRes.BookRes> booklist){
        return BookDtoRes.searchBookList.builder()
                .bookResList(booklist)
                .build();
    }
}
