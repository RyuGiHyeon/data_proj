package data_project.health.attendance.dto;

import lombok.Builder;
import lombok.Getter;

public class AtdDtoRes {

    @Builder
    @Getter
    public static class enrollUser{

        private Long userId;
    }
}
