package com.disorder.chatRoom.Service;

import com.disorder.chatRoom.DTO.ChatRoomDTO;
import com.disorder.chatRoom.Entity.ChatRoom;
import com.disorder.chatRoom.Repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService{
    private final ChatRoomRepository repository;
    private final ModelMapper modelMapper;


    @Override
    public List<ChatRoomDTO> getAll() {
        return repository.findAll()
                .stream().map(this::EntityToDTO)
                .collect(Collectors.toList());
    }

    private ChatRoomDTO EntityToDTO(ChatRoom chatRoom){
        return modelMapper.map(chatRoom, ChatRoomDTO.class);
    }
}
