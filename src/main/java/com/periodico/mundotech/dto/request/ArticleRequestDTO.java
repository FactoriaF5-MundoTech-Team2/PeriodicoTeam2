package com.periodico.mundotech.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArticleRequestDTO {
    @NotBlank(message = "El título no puede estar vacío")
    private String title;

    private String content;

    private LocalDate publishDate;

    private Long authorId;
}
