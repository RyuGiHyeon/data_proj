package data_project.health.user.service;

import data_project.health.locker.dto.BookDtoRes;
import data_project.health.user.dto.UserDtoReq;
import data_project.health.user.dto.UserDtoRes;

import java.util.Date;

public interface UserService {
    UserDtoRes.enrollUser signUp(UserDtoReq.enrollUser request);

    UserDtoRes.userAttendance attendance(UserDtoReq.attendance request);

    Integer calculateDaysBetween(Date updatedAt);
}
