package data_project.health.global.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    // 공용 처리
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "400", "Invalid parameter included"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "404", "Resource not exists"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "알수없는 에러 관리자에게 문의"),


    //user error (4001~
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"4001","해당 유저를 찾을 수 없습니다."),
    USER_PHONE_DUPLICATE(HttpStatus.BAD_REQUEST,"4002","중복된 휴대폰 번호가 존재합니다."),
    USER_MEMBERSHIP_EXPIRED(HttpStatus.EXPECTATION_FAILED, "4003", "회원권이 만료되었습니다."),


    //book error(4010~
    USER_BOOk_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "4010", "대여한 책이 없습니다."),
    BOOk_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "4011", "해당하는 책이 존재하지 않습니다."),
    BOOk_NOT_RENT(HttpStatus.INTERNAL_SERVER_ERROR, "4012", "해당책은 대여중입니다. "),
    RENT_BOOk_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "4010", "대여된 책이 아닙니다."),


    //email error(5010~
    EMAIL_SEND_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "5010", "메일 전송이 실패되었습니다."),
    NO_SUCH_ALGORITHM(HttpStatus.INTERNAL_SERVER_ERROR, "5011", "알고리즘 사용 불가능합니다."),
    EMAIL_VERIFY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "5012", "인증이 만료됐습니다."),


    //DataBase error(5001~
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "5000", "데이터베이스 에러")



    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
