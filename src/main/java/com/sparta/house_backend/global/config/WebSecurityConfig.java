package com.sparta.house_backend.global.config;

import com.sparta.house_backend.domain.member.entity.MemberRoleEnum;
import com.sparta.house_backend.global.jwt.JwtAuthenticationFilter;
import com.sparta.house_backend.global.jwt.JwtAuthorizationFilter;
import com.sparta.house_backend.global.jwt.JwtUtil;
import com.sparta.house_backend.global.security.MemberDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Spring Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    private final JwtUtil jwtUtil;
    private final MemberDetailsServiceImpl memberDetailsService;
    private final AuthenticationConfiguration authenticationConfiguration;

    public WebSecurityConfig(JwtUtil jwtUtil, MemberDetailsServiceImpl memberDetailsService, AuthenticationConfiguration authenticationConfiguration) {
        this.jwtUtil = jwtUtil;
        this.memberDetailsService = memberDetailsService;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtUtil);
        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        return filter;
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(jwtUtil, memberDetailsService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 설정
        http.csrf((csrf) -> csrf.disable());

        // 기본 설정인 Session 방식은 사용하지 않고 JWT 방식을 사용하기 위한 설정
        http.sessionManagement((sessionManagement) ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                        .requestMatchers(HttpMethod.POST,"/api/interior").hasRole(String.valueOf(MemberRoleEnum.ADMIN))
                        .requestMatchers(HttpMethod.PUT,"/api/interior/{interiorId}").hasRole(String.valueOf(MemberRoleEnum.ADMIN))
                        .requestMatchers(HttpMethod.DELETE,"/api/interior/{interiorId}").hasRole(String.valueOf(MemberRoleEnum.ADMIN))
                        .anyRequest().permitAll()
//                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // resources 접근 허용 설정
//                        .requestMatchers("/api/member/**").permitAll() // '/api/user/'로 시작하는 요청 모두 접근 허가
//                        .anyRequest().authenticated() // 그 외 모든 요청 인증처리
        );

//        http.formLogin((formLogin) ->
//                formLogin
//                        .loginPage("/api/member/sign-page").permitAll()
//        );

        // 필터 관리
        http.addFilterBefore(jwtAuthorizationFilter(), JwtAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
