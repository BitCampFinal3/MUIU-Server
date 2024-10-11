package com.bit.muiu.entity;

import com.bit.muiu.dto.DiaryDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@SequenceGenerator(
        name = "diarySeqGenerator",
        sequenceName = "DIARY_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Diary {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "diarySeqGenerator"
    )
    private Long diaryId; // 다이어리의 고유 ID (Primary Key)

    @Column(nullable = false)
    private Long id;  // 유저의 고유 ID 또는 null 허용

    @Column(length = 50, nullable = true)
    private String mood; // 기분

    @Column(length = 100, nullable = true)
    private String title; // 제목

    @Column(length = 2000, nullable = true)
    private String content; // 내용

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime regdate; // 등록 날짜 (자동 생성)

    @UpdateTimestamp
    private LocalDateTime moddate; // 수정 날짜 (자동 갱신)

    // Diary 엔티티를 DiaryDto로 변환
    public DiaryDto toDto() {
        return DiaryDto.builder()
                .diaryId(this.diaryId)
                .id(this.id)  // id가 있을 경우에만 변환
                .mood(this.mood)
                .title(this.title)
                .content(this.content)
                .regdate(this.regdate)
                .moddate(this.moddate)
                .build();
    }
}