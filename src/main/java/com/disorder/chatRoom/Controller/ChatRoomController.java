package com.disorder.chatRoom.Controller;

import com.disorder.chatRoom.DTO.ChatRoomDTO;
import com.disorder.chatRoom.Service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/chatroom")
@RestController
public class ChatRoomController {
    private final ChatRoomService service;

    @GetMapping("/get-all")
    public List<ChatRoomDTO> getAll(){
        return service.getAll();
    }
}
