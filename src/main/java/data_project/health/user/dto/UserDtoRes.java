package data_project.health.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

public class UserDtoRes {

    @Builder
    @Getter
    public static class enrollUser{
        private String userId;
    }

    @Builder
    @Getter
    public static class userAttendance{
        private String userId;
        private String gender;
        private Integer expiredAt;
    }

    @Builder
    @Getter
    public static class userDetails{
        private String userId;
        private String gender;
        private Date updatedAt;
    }
}
