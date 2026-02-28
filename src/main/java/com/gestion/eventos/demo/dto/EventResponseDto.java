package com.gestion.eventos.demo.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"id", "name", "date", "location"})
public class EventResponseDto {
    private Long id;
    private String name;
    private LocalDate date;
    private String location;
}
