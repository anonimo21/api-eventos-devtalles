package com.gestion.eventos.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Detalles necesarios para crear o actualizar un evento")
public class EventRequestDto {
    @Schema(description = "Nombre del evento", example = "Conferencia de Tecnología 2024")
    @NotBlank(message = "El nombre del evento no puede estar vacío.")
    private String name;

    @NotNull(message = "La fecha no puede ser nula.")
    @Schema(description = "Fecha del evento", example = "2024-06-15")
    private LocalDate date;

    @NotBlank(message = "La ubicación no puede estar vacía.")
    @Schema(description = "Ubicación del evento", example = "Centro de Convenciones")
    private String location;

    @NotNull(message = "La categoría es obligatoria.")
    @Schema(description = "ID de la categoría del evento", example = "1")
    private Long categoryId;

    private Set<Long> speakersIds;
}
