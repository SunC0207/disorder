package com.disorder.infomation.Service;

import com.disorder.infomation.DTO.InfoDTO;
import com.disorder.infomation.Entity.Info;
import com.disorder.infomation.Repository.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService{
    private final InfoRepository repository;
    private final ModelMapper modelMapper;
    @Override
    public List<InfoDTO> getAll() {
        return repository.findAllOrdByDate()
                .stream().map(this::EntityToDTO)
                .collect(Collectors.toList());
    }

    private InfoDTO EntityToDTO(Info info){
        return modelMapper.map(info, InfoDTO.class);
    }
}
