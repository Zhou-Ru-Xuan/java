package com.zhouruxuan.util.mapstruct.mapper;

import com.zhouruxuan.util.mapstruct.entity.Patient;
import com.zhouruxuan.util.mapstruct.entity.PatientDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);
    PatientDto toDto(Patient patient);
}