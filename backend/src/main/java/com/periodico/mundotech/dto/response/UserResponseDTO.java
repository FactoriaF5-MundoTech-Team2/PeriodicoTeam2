package com.periodico.mundotech.dto.response;

import java.util.Set;
import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;

    private String name;

    private String email;

    private Set<String> roles;
}
