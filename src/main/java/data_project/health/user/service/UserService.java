package data_project.health.user.service;

import data_project.health.locker.dto.BookDtoRes;
import data_project.health.user.dto.UserDtoReq;
import data_project.health.user.dto.UserDtoRes;

public interface UserService {
    UserDtoRes.enrollUser signUp(UserDtoReq.enrollUser request);

//    BookDtoRes.searchMyBookList rentBookSearch(Long user_id);
}
