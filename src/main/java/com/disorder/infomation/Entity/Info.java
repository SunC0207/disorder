package com.disorder.infomation.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Info {
    @Id
    @GeneratedValue
    private Integer id;
    private String subject;
    @Column(length = 1000)
    private String content;
    private String image;
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date date;
}
