package data_project.health.user.service;

import data_project.health.user.dto.UserDtoReq;
import data_project.health.user.dto.UserDtoRes;

import java.util.Date;

public interface UserService {
    UserDtoRes.enrollUser signUp(UserDtoReq.enrollUser request);

    UserDtoRes.userAttendanceB attendance(UserDtoReq.attendance request);

    UserDtoRes.userDetails details(UserDtoReq.userByUserId request);

    Integer calculateDaysBetween(Date updatedAt);
}
