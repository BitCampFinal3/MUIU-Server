package com.bit.muiu.controller;

import com.bit.muiu.dto.DiaryDto;
import com.bit.muiu.dto.ResponseDto;
import com.bit.muiu.service.DiaryService;
import com.bit.muiu.entity.Member;
import com.bit.muiu.repository.MemberRepository; // 추가된 부분
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diaries")
@RequiredArgsConstructor
@Slf4j
public class DiaryController {

    private final DiaryService diaryService;
    private final MemberRepository memberRepository; // 추가: 회원 정보 조회를 위한 repository

    @PostMapping("/write")
    public ResponseEntity<?> writeDiary(@RequestBody DiaryDto diaryDto) {
        ResponseDto<DiaryDto> responseDto = new ResponseDto<>();

        try {
            // 현재 인증된 사용자 정보 가져오기
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal(); // 로그인한 사용자 정보
            String username = userDetails.getUsername(); // username 가져오기

            // username을 통해 Member 엔티티에서 id를 가져옴
            Member member = memberRepository.findByUsername(username)
                    .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + username));

            Long memberId = member.getId();  // Member의 id 값 가져오기
            log.info("Writing diary for member ID: {}", memberId); // 로그로 확인

            // diaryDto에 memberId 설정
            diaryDto.setId(memberId);

            // 다이어리 저장 로직 호출
            DiaryDto savedDiary = diaryService.writeDiary(diaryDto);

            responseDto.setStatusCode(HttpStatus.CREATED.value());
            responseDto.setStatusMessage("Diary created successfully");
            responseDto.setItem(savedDiary);

            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            log.error("Error while writing diary: {}", e.getMessage());
            responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDto.setStatusMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(responseDto);
        }
    }
}
