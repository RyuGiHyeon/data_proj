package data_project.health.book.service;

import data_project.health.book.dto.BookDtoReq;
import data_project.health.book.dto.BookDtoRes;

public interface BookService {
    BookDtoRes.bookIdRes bookEnroll(BookDtoReq.bookEnroll request);

    void bookUpdate(BookDtoReq.bookUpdate request);

    void bookDelete(Long bookId);

    BookDtoRes.searchBookList bookSearch();

    BookDtoRes.searchBookList bookRentSearch();
}