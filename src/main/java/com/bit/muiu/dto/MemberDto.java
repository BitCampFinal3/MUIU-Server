package com.bit.muiu.dto;

import com.bit.muiu.entity.Member;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Date birth;
    private String gender;
    private String tel;
    private String role;
    private boolean locationAgree;
    private boolean recordConsent;
    private String token; // 토큰은 토큰

    public Member toEntity() {
        return Member.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .email(this.email)
                .birth(this.birth)
                .gender(this.gender)
                .tel(this.tel)
                .role(this.role)
                .locationAgree(this.locationAgree)
                .recordConsent(this.recordConsent)
                .build();
    }
}