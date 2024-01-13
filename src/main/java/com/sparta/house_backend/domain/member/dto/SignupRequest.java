package com.sparta.house_backend.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupRequest {
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;
    @NotBlank(message = "비밀번호은 필수 입력 항목입니다.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\",./<>?])(?=.*[a-zA-Z0-9]).{8,15}$",
            message = "최소 8자 이상, 15자 이하의 대소문자, 숫자, 특수문자를 포함해야 합니다."
    )
    private String password;
    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String username;
    @NotBlank(message = "전화번호는 필수 입력 항목입니다.")
    private String phoneNumber;
    @NotNull // 공백은 허용
    private String address;
    @NotNull // 일단 현재(1월 13일 기준 모든 회원가입 유저는 USER 로 관리)
    private boolean role;
}
