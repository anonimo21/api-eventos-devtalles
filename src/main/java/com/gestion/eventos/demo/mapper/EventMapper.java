package com.gestion.eventos.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.gestion.eventos.demo.domain.Event;
import com.gestion.eventos.demo.dto.EventRequestDto;
import com.gestion.eventos.demo.dto.EventResponseDto;

@Mapper(componentModel = "spring")
public interface EventMapper {

    Event toEntity(EventRequestDto eventRequestDto);
    EventResponseDto toEventResponseDto(Event event);

    List<EventResponseDto> toEventResponseDtoList(List<Event> events);

    void updateEventFromDto(EventRequestDto dto, @MappingTarget Event event);
}
