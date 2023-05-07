package com.disorder.chatRoom.Repository;

import com.disorder.chatRoom.Entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
}
