package data_project.health.trainer.service;

import data_project.health.trainer.dto.TrainerDtoReq;
import data_project.health.trainer.dto.TrainerDtoRes;

public interface TrainerService {
    TrainerDtoRes.RentBookRes rentBook(TrainerDtoReq.rentBookReq request);

    void returnBook(Long rentBookId);

    void renewBook(Long rentBookId);
}
