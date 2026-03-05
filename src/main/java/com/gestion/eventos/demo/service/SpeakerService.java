package com.gestion.eventos.demo.service;

import com.gestion.eventos.demo.domain.Speaker;
import com.gestion.eventos.demo.dto.SpeakerRequestDto;
import java.util.List;

public interface SpeakerService {
    Speaker save(SpeakerRequestDto requestDto);

    Speaker findById(Long id);

    List<Speaker> findAll();

    Speaker update(Long id, SpeakerRequestDto requestDto);

    void deleteById(Long id);
}