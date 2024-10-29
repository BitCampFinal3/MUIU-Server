package com.bit.muiu.service;

import com.bit.muiu.entity.ChatRoom;
import com.bit.muiu.entity.Member;
import com.bit.muiu.repository.ChatRoomRepository;
import com.bit.muiu.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ChatRoomService(ChatRoomRepository chatRoomRepository, MemberRepository memberRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.memberRepository = memberRepository;
    }

    public Long findOrCreateChatRoom(Long userId) {
        Member currentUser = memberRepository.findById(userId).orElseThrow();

        // 1. 먼저 기존 ChatRoom을 확인
        Optional<ChatRoom> existingRoom = chatRoomRepository.findByUser1OrUser2(currentUser, currentUser);
        if (existingRoom.isPresent()) {
            ChatRoom chatRoom = existingRoom.get();
            Long partnerId = chatRoom.getUser1().equals(currentUser) ? chatRoom.getUser2().getId() : chatRoom.getUser1().getId();
            return partnerId;  // 기존 채팅방이 있을 경우 partnerId 반환
        }

        // 2. 기존 ChatRoom이 없는 경우, WAITING 상태의 사용자를 찾고 새 ChatRoom 생성
        Optional<Member> waitingUser = memberRepository.findFirstByStatusAndIdNot("WAITING", userId);
        if (waitingUser.isPresent()) {
            Member partner = waitingUser.get();

            // 상태를 'BUSY'로 설정하여 매칭 확정
            currentUser.setStatus("BUSY");
            partner.setStatus("BUSY");
            memberRepository.save(currentUser);
            memberRepository.save(partner);

            // 새로운 ChatRoom 생성 및 저장
            ChatRoom newRoom = new ChatRoom();
            newRoom.setUser1(currentUser);
            newRoom.setUser2(partner);
            chatRoomRepository.save(newRoom);

            return partner.getId();
        }

        return null;
    }
}