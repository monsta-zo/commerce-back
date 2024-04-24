package org.example.commerceback.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRequest {

    // 회원가입 요청 DTO
    public record RegisterDTO(
            @NotBlank(message = "이메일을 입력해주세요.")
            @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "올바른 이메일 형식을 입력해주세요.")
            String email,
            @NotBlank(message="이름을 입력해주세요.")
            @Size(min=2, max=50, message = "이름은 2자에서 20자 이내여야 합니다.")
            String name,
            @NotBlank(message = "비밀번호를 입력해주세요.")
            @Size(min=8, max=50, message = "비밀번호는 8자에서 20자 이내여야 합니다.")
            @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!~`<>,./?;:'\"\\[\\]{}\\\\()|_-])\\S*$", message = "올바른 비밀번호 형식을 입력해주세요.")
            String password,
            @NotBlank(message = "확인 비밀번호를 입력해주세요.")
            @Size(min=8, max=50, message = "비밀번호는 8자에서 20자 이내여야 합니다.")
            @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!~`<>,./?;:'\"\\[\\]{}\\\\()|_-])\\S*$", message = "올바른 비밀번호 형식을 입력해주세요.")
            String passwordConfirm
    ) { }

    public record LoginDTO(
            @NotBlank(message = "이메일을 입력해주세요.")
            String email,
            @NotBlank(message = "비밀번호를 입력해주세요.")
            String password
    ) { }
}
