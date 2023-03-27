package com.zhouruxuan.util.mapstruct.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Doctor2 {
    private int id;
    private String name;
    private String specialty;
    private List<Patient> patientList;
}