package data_project.health.user.service;

import data_project.health.attendance.repository.AttendanceRepository;
import data_project.health.global.exception.BusinessException;
import data_project.health.global.exception.errorcode.CommonErrorCode;
import data_project.health.user.dto.PostLockerNumber;
import data_project.health.user.dto.PostTrainingClass;
import data_project.health.user.dto.UserDtoReq;
import data_project.health.user.dto.UserDtoRes;
import data_project.health.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;

    // 회원 등록
    @Override
    @Transactional
    public UserDtoRes.enrollUser signUp(UserDtoReq.enrollUser request) {

        // 전화번호 중복 확인 (등록된 회원인지)
        if (userRepository.dupPhone(request.getPhone())) {
            throw new BusinessException(CommonErrorCode.USER_PHONE_DUPLICATE);
        }

        // 사용자 등록
        Long userId = userRepository.signUp(request);

        return UserDtoRes.enrollUser.builder()
                .userId(userId)
                .build();
    }

    // usderId를 통해서 회원 정보가져오는 서비스
    @Override
    public UserDtoRes.userDetails details(UserDtoReq.userByUserId request) {
        return userRepository.getUserDetails(request.getUserId());
    }

    @Override
    public UserDtoRes.TrainingClass training(PostTrainingClass request) {
        Long userId = userRepository.postTrainingClass(request);
        return UserDtoRes.TrainingClass.builder().userId(userId).build();

    }

    @Override
    public UserDtoRes.PostLocker postlocker(PostLockerNumber request) {
        Long lockerId = userRepository.postLockerNumber(request);
        return UserDtoRes.PostLocker.builder().lockerId(lockerId).build();

    }

    // 회원 출석
    @Override
    public UserDtoRes.userAttendanceB attendance(UserDtoReq.attendance request) {
        UserDtoRes.userAttendanceA userAttendanceA = userRepository.getUserBasic(request.getUserId());
        Integer expiredAt = calculateDaysBetween(userAttendanceA.getUpdatedAt());

        if (expiredAt >= 0)
            attendanceRepository.attendance(userAttendanceA.getUserId());
        else
            throw new BusinessException(CommonErrorCode.USER_MEMBERSHIP_EXPIRED);


        return UserDtoRes.userAttendanceB.builder()
                .userId(userAttendanceA.getUserId())
                .gender(userAttendanceA.getGender())
                .expiredAt(expiredAt)
                .build();
    }

    // 남은 회원권 일수 계산
    @Override
    public Integer calculateDaysBetween(Date updatedAt) {

        Date now = new Date();

        // 두 날짜의 차이를 밀리초 단위로 계산, 일 단위로 변환
        long diffInMillis = now.getTime() - updatedAt.getTime();
        long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

        return (int) diffInDays;
    }
}
