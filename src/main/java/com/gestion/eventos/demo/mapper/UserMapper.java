package com.gestion.eventos.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gestion.eventos.demo.domain.User;
import com.gestion.eventos.demo.dto.RegisterDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "id", ignore = true)
    User registerDtoToUser(RegisterDto registerDto);
}
