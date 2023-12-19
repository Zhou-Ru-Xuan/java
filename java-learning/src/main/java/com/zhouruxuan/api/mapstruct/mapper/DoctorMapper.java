package com.zhouruxuan.api.mapstruct.mapper;

import com.zhouruxuan.api.mapstruct.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    @Mapping(target = "dtoName", source = "doctor.name")
    @Mapping(source = "doctor.specialty", target = "specialization")
    @Mapping(source = "education.degreeName", target = "degree")
    DoctorDto toDto(Doctor doctor, Education education);

    List<DoctorDto> toDtos(List<Doctor> doctors);

    @Mapping(source = "doctor.patientList", target = "patientDtoList")
    @Mapping(source = "doctor.specialty", target = "specialization")
    DoctorDto2 toDto2(Doctor2 doctor);
}