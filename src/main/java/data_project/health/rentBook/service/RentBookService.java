package data_project.health.rentBook.service;

import data_project.health.rentBook.dto.RentBookDtoReq;
import data_project.health.rentBook.dto.RentBookDtoRes;

public interface RentBookService {
    RentBookDtoRes.RentBookRes rentBook(RentBookDtoReq.rentBookReq request);

    void returnBook(Long rentBookId);

    void renewBook(Long rentBookId);
}
