package com.zhouruxuan.api.mapstruct.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private int id;
    private String dtoName;
    private String degree;
    private String specialization;
}