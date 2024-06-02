package data_project.health.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Date;


public class UserDtoReq {

    @Builder
    @Setter
    @Getter
    public static class enrollUser{

        // 이름, 휴대폰, 성별, 회원권 시작, 회원권 만료
        @NotBlank(message = "이름은 필수 입력 값입니다.")
        private String name;
        @NotBlank(message = "전화번호 뒷 네자리는 필수 입력 값입니다.")
        private String phone;
        @NotBlank(message = "성별을 입력해주세요.\n 예) (남자, 여자)")
        private String gender;
        @NotNull(message = "회원권 시작 일자는 필수 입력 값입니다.")
        private Date createdAt;
        @NotNull(message = "회원권 만료 일자는 필수 입력 값입니다.")
        private Date updatedAt;
    }

    @Builder
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class userByPhone {
        @NotBlank(message = "전화번호 뒷 8자리를 입력해주세요")
        private String phone;
    }
}
