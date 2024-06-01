package data_project.health.attendance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class AtdDtoReq {

    @Builder
    @Setter
    @Getter
    public static class mkAttendance{

        private Long userId;
    }
}
