package com.sparta.house_backend.member.dto;

import com.sparta.house_backend.member.entity.MemberRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SignupRequest {
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;
    @NotNull(message = "비밀번호를 입력하세요.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\",./<>?])(?=.*[a-zA-Z0-9]).{8,15}$",
            message = "최소 8자 이상, 15자 이하의 대소문자, 숫자, 특수문자를 포함해야 합니다."
    )
    private String password;
    @NotNull
    private String username;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String address;
    @NotNull
    private boolean role;
}
