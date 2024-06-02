package data_project.health.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public class UserDtoRes {

    @Builder
    @Getter
    public static class enrollUser{
        private Long userId;
    }

    @Builder
    @Getter
    public static class userAttendanceB {
        private Long userId;
        private String gender;
        private Integer expiredAt;
    }

    @Builder
    @Getter
    public static class userAttendanceA {
        private Long userId;
        private String name;
        private String gender;
        private Date updatedAt;
    }



    @Builder
    @Getter
    @Setter
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

    @Builder
    @Getter
    public static class TrainingClass {
        private Long userId;
    }
    @Builder
    @Getter
    public static class PostLocker {
        private Long lockerId;
    }

}
