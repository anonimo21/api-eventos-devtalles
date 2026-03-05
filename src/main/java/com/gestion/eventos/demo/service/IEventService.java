package com.gestion.eventos.demo.service;

import com.gestion.eventos.demo.domain.Event;
import com.gestion.eventos.demo.dto.EventRequestDto;
import com.gestion.eventos.demo.dto.EventResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEventService {
    Page<EventResponseDto> findAll(String name, Pageable pageable);

    Event save(EventRequestDto event);

    Event findById(Long id);

    Event update(Long id, EventRequestDto requestDto);

    void deleteById(Long id);
}
