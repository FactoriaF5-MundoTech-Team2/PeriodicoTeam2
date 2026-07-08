package com.periodico.mundotech.dto.response;

import java.time.LocalDate;
import com.periodico.mundotech.entity.enums.ArticleStatus;
import lombok.Data;

@Data
public class ArticleResponseDTO {
    private Long id;

    private String title;

    private String content;

    private LocalDate publishDate;

    private ArticleStatus status;

    private String imageUrl;

    private String authorName;
}
