package com.gestion.eventos.demo.mapper;

import com.gestion.eventos.demo.domain.Category;
import com.gestion.eventos.demo.dto.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto categoryDto);
}
