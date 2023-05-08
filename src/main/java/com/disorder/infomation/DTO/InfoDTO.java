package com.disorder.infomation.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Blob;
import java.sql.Date;

@RequiredArgsConstructor
@Data
public class InfoDTO {
    private Integer id;
    private String subject;
    private String content;
    private String image;
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date date;
}
