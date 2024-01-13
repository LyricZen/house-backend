package com.sparta.house_backend.domain.member.service;

import com.sparta.house_backend.domain.member.entity.Member;
import com.sparta.house_backend.domain.member.entity.MemberRoleEnum;
import com.sparta.house_backend.domain.member.dto.MemberResponse;
import com.sparta.house_backend.domain.member.dto.SignupRequest;
import com.sparta.house_backend.domain.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public MemberResponse signup(SignupRequest request) {
        String email = request.getEmail();
        // 비밀번호 인코드
        String enterPassword = request.getPassword();
        if(enterPassword == null || enterPassword.isEmpty()) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요.");
        }
        String password = passwordEncoder.encode(enterPassword);

        // email 중복확인
        Optional<Member> checkEmail = memberRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        String username = request.getUsername();
        String phoneNumber = request.getPhoneNumber();
        String address = request.getAddress();

        // 사용자 역할확인 테스트를 위해 일단 admin 으로 설정 중!
        MemberRoleEnum role = MemberRoleEnum.USER;
        // 어드민 권한 설정은 어떻게??
//        if (request.isRole()) {
//            if ()
//        }

        Member member = new Member(email, password, username, phoneNumber, address, role);
        memberRepository.save(member);
        return new MemberResponse(member);
    }
}


