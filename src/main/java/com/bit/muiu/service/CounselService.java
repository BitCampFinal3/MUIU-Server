package com.bit.muiu.service;

import com.bit.muiu.entity.Counsel;
import com.bit.muiu.repository.CounselRepository;
import com.bit.muiu.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CounselService {


    @Transactional
    public void migrateExistingData() {
//        // chat_room 데이터 변환 로그
//        List<Counsel> chatCounsels = chatRoomRepository.findAll().stream()
//                .map(chatRoom -> chatRoom.toCounsel())
//                .toList();
//        chatCounsels.forEach(counsel -> System.out.println("Chat Counsel: " + counsel));
//
//        List<Counsel> callCounsels = callRoomRepository.findAll().stream()
//                .map(callRoom -> callRoom.toCounsel())
//                .toList();
//        callCounsels.forEach(counsel -> System.out.println("Call Counsel: " + counsel));
//
//        List<Counsel> videoCounsels = videoRoomRepository.findAll().stream()
//                .map(videoRoom -> videoRoom.toCounsel())
//                .toList();
//        videoCounsels.forEach(counsel -> System.out.println("Video Counsel: " + counsel));
//
//        // 변환된 데이터 저장
//        counselRepository.saveAll(chatCounsels);
//        counselRepository.saveAll(callCounsels);
//        counselRepository.saveAll(videoCounsels);
    }


}
