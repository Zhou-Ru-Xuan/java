package com.zhouruxuan.api.mapstruct.mapper;

import com.zhouruxuan.api.mapstruct.entity.Patient;
import com.zhouruxuan.api.mapstruct.entity.PatientDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);
    PatientDto toDto(Patient patient);
}