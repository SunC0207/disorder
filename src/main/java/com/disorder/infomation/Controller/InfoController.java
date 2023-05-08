package com.disorder.infomation.Controller;

import com.disorder.infomation.DTO.InfoDTO;
import com.disorder.infomation.Service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/info")
@CrossOrigin("*")
public class InfoController {
    private final InfoService service;
    @GetMapping("/get-all")
    public List<InfoDTO> getAll(){
        return service.getAll();
    }
}
