package data_project.health.user.service;

import data_project.health.user.dto.PostLockerNumber;
import data_project.health.user.dto.PostTrainingClass;
import data_project.health.user.dto.UserDtoReq;
import data_project.health.user.dto.UserDtoRes;

import java.util.Date;

public interface UserService {
    UserDtoRes.enrollUser signUp(UserDtoReq.enrollUser request);

    UserDtoRes.userAttendanceB attendance(UserDtoReq.userByPhone request);

    UserDtoRes.userDetails details(UserDtoReq.userByPhone request);

    UserDtoRes.TrainingClass training(PostTrainingClass request);

    UserDtoRes.PostLocker postlocker(PostLockerNumber request);

    Integer calculateDaysBetween(Date updatedAt);
}
