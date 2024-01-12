package com.sparta.house_backend.member.dto;

import com.sparta.house_backend.member.entity.Member;
import com.sparta.house_backend.member.entity.MemberRoleEnum;
import lombok.Getter;

@Getter
public class MemberResponse {
    private Long memberId;
    private String email;
    private String password;
    private String username;
    private String phoneNumber;
    private String address;
    private MemberRoleEnum role;

    public MemberResponse(Member member) {
        this.memberId = member.getMemberId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.username = member.getUsername();
        this.phoneNumber = member.getPhoneNumber();
        this.address = member.getAddress();
        this.role = member.getRole();
    }
}
