package com.gestion.eventos.demo.controller;

import com.gestion.eventos.demo.domain.Event;
import com.gestion.eventos.demo.dto.EventRequestDto;
import com.gestion.eventos.demo.dto.EventResponseDto;
import com.gestion.eventos.demo.mapper.EventMapper;
import com.gestion.eventos.demo.service.IEventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
@Tag(name = "Event", description = "Operacones relacionadas con la gestion de eventos")
public class EventController {

    private final IEventService eventService;
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);
    private final EventMapper eventMapper;

    @GetMapping("/problematic")
    @Operation(summary = "Obtener todos los eventos con todos sus detalles", description = "Obtiene todos los eventos con todos sus detalles")
    public ResponseEntity<List<Event>> getEventsProblematic(@RequestParam String param) {
        logger.info("Recibida la Solicitud GET /events con el parametro '{}'", param);
        List<Event> events = eventService.getAllEventsAndTheirDetailsProblematic();
        logger.info("Total de eventos encontrados: {}", events.size());
        return ResponseEntity.ok(events);
    }

    @GetMapping("/optimized-join-fetch")
    @Operation(summary = "Obtener todos los eventos con todos sus detalles", description = "Obtiene todos los eventos con todos sus detalles")
    public ResponseEntity<List<Event>> getEventsOptimizedWithJoinFetch() {
        logger.info("Recibida la Solicitud GET /events con el parametro '{}'");
        List<Event> events = eventService.getAllEventsAndTheirDetailsOptimizedWithJoinFetch();
        logger.info("Total de eventos encontrados: {}", events.size());
        return ResponseEntity.ok(events);
    }

    @GetMapping("/optimized/all-details")
    @Operation(summary = "Obtener todos los eventos con todos sus detalles", description = "Obtiene todos los eventos con todos sus detalles")
    public ResponseEntity<List<Event>> getEventsWithAllDetails() {
        logger.info("Recibida la Solicitud GET /events con el parametro '{}'");
        List<Event> events = eventService.findAllEventsWithAllDetailsOptimized();
        logger.info("Total de eventos encontrados: {}", events.size());
        return ResponseEntity.ok(events);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "Obtener todos los eventos", description = "Obtiene todos los eventos")
    public ResponseEntity<Page<EventResponseDto>> getAllEvents(
            @RequestParam(required = false) String name,
            @PageableDefault(page = 0, size = 10, sort = "name") Pageable pageable

    ) {
        logger.info("Recibida la Solicitud GET /events con en el nombre '{}' y la paginacion '{}'", name, pageable);
        Page<EventResponseDto> events = eventService.findAll(name, pageable);
        logger.info("Total de eventos encontrados: {}", events.getTotalElements());
        return ResponseEntity.ok(events);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Crear un evento", description = "Crea un evento")
    public ResponseEntity<EventResponseDto> createEvent(@Valid @RequestBody EventRequestDto requestDto) {
        logger.info("Creando evento: {}", requestDto.getName());
        Event eventSaved = eventService.save(requestDto);
        EventResponseDto responseDto = eventMapper.toResponseDto(eventSaved);
        logger.debug("Evento creado: {}", eventSaved.getName());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "Obtener un evento por ID", description = "Obtiene un evento por su ID")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Evento encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Evento no encontrado")
        }
    )
    public ResponseEntity<EventResponseDto> getEventById(@PathVariable Long id) {
        logger.info("Recibida la Solicitud GET /events con el id '{}'", id);
        Event event = eventService.findById(id);
        EventResponseDto responseDto = eventMapper.toResponseDto(event);
        logger.info("Evento encontrado: {}", event.getName());
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Actualizar un evento por ID", description = "Actualiza un evento por su ID")
    public ResponseEntity<EventResponseDto> updateEvent(@PathVariable Long id,
            @Valid @RequestBody EventRequestDto requestDto) {
        logger.info("Recibida la Solicitud PUT /events con el id '{}'", id);
        Event updateEvent = eventService.update(id, requestDto);
        logger.info("Evento actualizado: {}", updateEvent.getName());
        return ResponseEntity.ok(eventMapper.toResponseDto(updateEvent));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Eliminar un evento por ID", description = "Elimina un evento por su ID")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        logger.info("Recibida la Solicitud DELETE /events con el id '{}'", id);
        eventService.deleteById(id);
        logger.info("Evento eliminado: {}", id);
        return ResponseEntity.noContent().build();
    }

}
