package data_project.health.trainingClass.service;

import data_project.health.trainingClass.dto.TrainingDtoReq;
import data_project.health.trainingClass.dto.TrainingDtoRes;

public interface TrainingService {
    TrainingDtoRes.bookIdRes bookEnroll(TrainingDtoReq.bookEnroll request);

    void bookUpdate(TrainingDtoReq.bookUpdate request);

    void bookDelete(Long bookId);

    TrainingDtoRes.searchBookList bookSearch();

    TrainingDtoRes.searchBookList bookRentSearch();
}