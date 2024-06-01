package data_project.health.trainer.dto;

import lombok.Getter;

import java.time.LocalDate;

public class TrainerDtoReq {


    @Getter
    public static class rentBookReq{
        private Long user_id;
        private Long book_id;
        private LocalDate return_day;
    }
}
