package data_project.health.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

public class UserDtoRes {

    @Builder
    @Getter
    public static class enrollUser{
        private String userId;
    }

    @Builder
    @Getter
    public static class userAttendanceB {
        private String userId;
        private String gender;
        private Integer expiredAt;
    }

    @Builder
    @Getter
    public static class userAttendanceA {
        private String userId;
        private String name;
        private String gender;
        private Date updatedAt;
    }

    @Builder
    @Getter
    public static class userDetails {
        private String name;
        private String gender;
        private String phone;
        private Date createdAt;
        private Date updatedAt;
        private String classNames;
        private String attendanceDates;
        private Integer locker;
    }

}
