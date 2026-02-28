package com.gestion.eventos.demo.service;

import java.util.List;

import com.gestion.eventos.demo.domain.Event;

public interface IEventService {
    List<Event> findAll();
    Event save(Event event);
    Event findById(Long id);
    void deleteById(Long id);
}
