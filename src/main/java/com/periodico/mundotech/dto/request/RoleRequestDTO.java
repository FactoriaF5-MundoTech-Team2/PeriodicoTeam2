package com.periodico.mundotech.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleRequestDTO {
    @NotBlank(message = "El nombre del rol es obligatorio")
    private String name;
}
