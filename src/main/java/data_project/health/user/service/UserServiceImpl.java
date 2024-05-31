package data_project.health.user.service;

import data_project.health.book.dto.BookDtoRes;
import data_project.health.global.exception.BusinessException;
import data_project.health.global.exception.errorcode.CommonErrorCode;
import data_project.health.user.converter.UserConverter;
import data_project.health.user.dto.UserDtoReq;
import data_project.health.user.dto.UserDtoRes;
import data_project.health.user.repository.UserRepository;
import data_project.health.util.EncryptHelper;
import data_project.health.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EncryptHelper encryptHelper;
    private final RedisUtil redisUtil;

    @Override
    @Transactional
    public UserDtoRes.enrollUser signUp(UserDtoReq.enrollUser request){


        // 이메일 중복 확인( 활동회원인지, 미인증 회원인지)
        if (userRepository.checkEmail(request.getEmail())==1){
            throw new BusinessException(CommonErrorCode.USER_EMAIL_DUPLICATE);
        }

        // 두 비밀번호 일치성 확인
        if(!request.getPassword().equals(request.getPasswordCheck())){
            throw new BusinessException(CommonErrorCode.USER_PASSWORD_NONEQULE);
        }

        try {
            Long userId = userRepository.signUp(request);
            return UserConverter.userIdRes(userId);
        } catch (Exception exception) {
            throw new BusinessException(CommonErrorCode.DATABASE_ERROR);
        }
    }


    //대여한 책 목록 조회
    @Override
    public BookDtoRes.searchMyBookList rentBookSearch(Long user_id){
        List<BookDtoRes.MyBookRes> list = userRepository.rentBookSearch(user_id);

        return UserConverter.rentBookList(list);
    }
}
