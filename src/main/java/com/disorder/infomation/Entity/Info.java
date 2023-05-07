package com.disorder.infomation.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String content;
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date date;
}
