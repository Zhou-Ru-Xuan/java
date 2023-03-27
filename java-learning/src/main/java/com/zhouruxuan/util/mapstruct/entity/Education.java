package com.zhouruxuan.util.mapstruct.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    private String degreeName;
    private String institute;
    private Integer yearOfPassing;
}