package data_project.health.locker.service;

import data_project.health.locker.dto.BookDtoReq;
import data_project.health.locker.dto.BookDtoRes;

public interface BookService {
    BookDtoRes.bookIdRes bookEnroll(BookDtoReq.bookEnroll request);

    void bookUpdate(BookDtoReq.bookUpdate request);

    void bookDelete(Long bookId);

    BookDtoRes.searchBookList bookSearch();

    BookDtoRes.searchBookList bookRentSearch();
}