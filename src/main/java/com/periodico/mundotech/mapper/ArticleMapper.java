package com.periodico.mundotech.mapper;

import org.springframework.stereotype.Component;

import com.periodico.mundotech.dto.request.ArticleRequestDTO;
import com.periodico.mundotech.dto.response.ArticleResponseDTO;
import com.periodico.mundotech.entity.Article;

@Component
public class ArticleMapper {
    public ArticleResponseDTO toResponseDTO(Article article){
        ArticleResponseDTO dto = new ArticleResponseDTO();
        dto.setId(article.getId());
        dto.setTitle(article.getTitle());
        dto.setContent(article.getContent());
        dto.setPublishDate(article.getPublishDate());
        dto.setImageUrl(article.getImage()!=null ? article.getImage().getFilePath():null);
        dto.setStatus(article.getStatus());
        dto.setAuthorName(article.getAuthor().getName());

        return dto;
    }

    public Article toEntity(ArticleRequestDTO dto){
        Article article = new Article();
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setPublishDate(dto.getPublishDate());

        return article;
    }

}
