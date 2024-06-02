package data_project.health.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.BindParam;

import java.util.Date;


public class UserDtoReq {

    @Builder
    @Setter
    @Getter
    public static class enrollUser{

        // 이름, 휴대폰, 성별, 회원권 시작, 회원권 만료
        @NotBlank(message = "이름은 필수 입력 값입니다.")
        private String name;
        @NotBlank(message = "전화번호는 필수 입력 값입니다.")
        @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "전화번호는 010-0000-0000 형식이어야 합니다.")
        private String phone;
        @NotBlank(message = "성별을 입력해주세요.\n 예) (남자, 여자)")
        private String gender;
        @NotBlank(message = "회원권 시작 일자는 필수 입력 값입니다.")
        private Date createdAt;
        @NotBlank(message = "회원권 만료 일자는 필수 입력 값입니다.")
        private Date updatedAt;
    }

    @Builder
    @Setter
    @Getter
    public static class attendance {
        @NotBlank(message = "전화번호 뒷 8자리를 입력해주세요")
        private Long userId;
    }

    @Builder
    @Setter
    @Getter
    public static class userByUserId {

        private Long userId;
    }

}
