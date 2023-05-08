package com.disorder.chatRoom.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ChatRoomDTO {
    private  Integer id;
    private String name;
    private String image;
}
