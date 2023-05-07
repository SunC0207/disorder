package com.disorder.infomation.Service;

import com.disorder.infomation.DTO.InfoDTO;
import com.disorder.infomation.Repository.InfoRepository;

import java.util.List;

public interface InfoService {
    List<InfoDTO> getAll();

}
