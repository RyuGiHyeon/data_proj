package data_project.health.user.dto;

import lombok.Builder;
import lombok.Getter;

public class UserDtoRes {

    @Builder
    @Getter
    public static class enrollUser{
        private String phone;
    }

    @Builder
    @Getter
    public static class userAttendance{
        private String userId;
    }
}
