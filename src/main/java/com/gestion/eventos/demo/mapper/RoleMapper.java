package com.gestion.eventos.demo.mapper;

import com.gestion.eventos.demo.domain.Role;
import com.gestion.eventos.demo.dto.RoleDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role role);

    Role toEntity(RoleDto roleDto);

    List<RoleDto> toDtoList(List<Role> roles); // Para UserResponseDto
}
