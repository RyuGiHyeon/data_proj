package data_project.health.user.service;

import data_project.health.locker.dto.BookDtoRes;
import data_project.health.global.exception.BusinessException;
import data_project.health.global.exception.errorcode.CommonErrorCode;
import data_project.health.user.converter.UserConverter;
import data_project.health.user.dto.UserDtoReq;
import data_project.health.user.dto.UserDtoRes;
import data_project.health.user.repository.UserRepository;
import data_project.health.util.EncryptHelper;
import data_project.health.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.ErrorState;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDtoRes.enrollUser signUp(UserDtoReq.enrollUser request) {

        // 전화번호 중복 확인 (등록된 회원인지)
        if (userRepository.dupPhone(request.getPhone())) {
            throw new BusinessException(CommonErrorCode.USER_PHONE_DUPLICATE);
        }

        // 사용자 등록
        Long userId = userRepository.signUp(request);

        // 응답 DTO 생성 및 반환
        return UserDtoRes.enrollUser.builder()
                .phone(request.getPhone())
                .build();
    }


//    //대여한 책 목록 조회
//    @Override
//    public BookDtoRes.searchMyBookList rentBookSearch(Long user_id){
//        List<BookDtoRes.MyBookRes> list = userRepository.rentBookSearch(user_id);
//
//        return UserConverter.rentBookList(list);
//    }
}
