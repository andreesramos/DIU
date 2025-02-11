package com.example.agenda.util;

import com.example.agenda.model.PersonDto;
import com.example.agenda.model.PersonVO;

import java.util.List;
import java.util.stream.Collectors;

public class PersonMapper {

    public static PersonVO personMapperDtoToEntity(PersonDto personDto){
        return PersonVO.builder()
                .id(personDto.getId())
                .nombre(personDto.getNombre())
                .apellidos(personDto.getApellidos())
                .calle(personDto.getCalle())
                .codigoPostal(personDto.getCodigoPostal())
                .ciudad(personDto.getCiudad())
                .fechaNacimiento(personDto.getFechaNacimiento())
                //.tutoriales(personDto.getTutoriales())
                .build();
    }

    public static PersonDto personMapperEntityToDto(PersonVO personVO){
        return PersonDto.builder()
                .id(personVO.getId())
                .nombre(personVO.getNombre())
                .apellidos(personVO.getApellidos())
                .calle(personVO.getCalle())
                .codigoPostal(personVO.getCodigoPostal())
                .ciudad(personVO.getCiudad())
                .fechaNacimiento(personVO.getFechaNacimiento())
                //.tutoriales(personVO.getTutoriales())
                .build();
    }

    public static List<PersonVO> personListMapperDtoToEntity(List<PersonDto> personDtoList) {
        return personDtoList.stream()
                .map(PersonMapper::personMapperDtoToEntity)
                .collect(Collectors.toList());
    }

    public static List<PersonDto> personListMapperEntityToDto(List<PersonVO> personVOList) {
        return personVOList.stream()
                .map(PersonMapper::personMapperEntityToDto)
                .collect(Collectors.toList());
    }
}
