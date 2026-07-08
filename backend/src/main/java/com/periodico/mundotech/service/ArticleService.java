package com.periodico.mundotech.service;

import java.util.List;

import com.periodico.mundotech.dto.request.ArticleRequestDTO;
import com.periodico.mundotech.dto.response.ArticleResponseDTO;
import com.periodico.mundotech.entity.enums.ArticleStatus;

public interface ArticleService {
    ArticleResponseDTO createArticle(ArticleRequestDTO dto);
    List<ArticleResponseDTO> findAll();
    ArticleResponseDTO findById(Long id);
    List<ArticleResponseDTO> findByIdAuthor(Long authorId);
    List<ArticleResponseDTO> findByStatus(ArticleStatus status);
    List<ArticleResponseDTO> findByIdAuthorAndStatus(Long authorId, ArticleStatus status);
    ArticleResponseDTO updateArticle(Long id, ArticleRequestDTO dto, Long authorId);
    void deleteById(Long id, Long authorId);
    ArticleResponseDTO submitForReview(Long id, Long authorId);
    ArticleResponseDTO approve(Long id);
    ArticleResponseDTO reject(Long id);

}
