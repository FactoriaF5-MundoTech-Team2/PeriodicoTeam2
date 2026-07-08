package com.periodico.mundotech.service.implement;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.periodico.mundotech.dto.request.ArticleRequestDTO;
import com.periodico.mundotech.dto.response.ArticleResponseDTO;
import com.periodico.mundotech.entity.Article;
import com.periodico.mundotech.entity.enums.ArticleStatus;
import com.periodico.mundotech.mapper.ArticleMapper;
import com.periodico.mundotech.repository.ArticleRepository;
import com.periodico.mundotech.repository.UserRepository;
import com.periodico.mundotech.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository,
            ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.articleMapper = articleMapper;
    }

    @Override
    public ArticleResponseDTO createArticle(ArticleRequestDTO dto) {
        Article article = articleMapper.toEntity(dto);
        article.setAuthor(userRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("El autor con ID " + dto.getAuthorId() + " no existe")));
        article.setStatus(ArticleStatus.DRAFT);
        if (article.getPublishDate() == null) {
            article.setPublishDate(LocalDate.now());
        }
        return articleMapper.toResponseDTO(articleRepository.save(article));
    }

    @Override
    public List<ArticleResponseDTO> findAll() {
        return articleRepository.findAll().stream()
                .map(articleMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleResponseDTO findById(Long id) {
        return articleMapper.toResponseDTO(articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El artículo con ID " + id + " no existe")));
    }

    @Override
    public List<ArticleResponseDTO> findByIdAuthor(Long authorId) {
        return articleRepository.findByAuthorId(authorId).stream()
                .map(articleMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponseDTO> findByStatus(ArticleStatus status) {
        return articleRepository.findByStatus(status).stream()
                .map(articleMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponseDTO> findByIdAuthorAndStatus(Long authorId, ArticleStatus status) {
        return articleRepository.findByIdAuthorAndStatus(authorId, status).stream()
                .map(articleMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleResponseDTO updateArticle(Long id, ArticleRequestDTO dto, Long authorId) {
        Article article = findArticleOrThrow(id);
        if (!article.getAuthor().getId().equals(authorId)) {
            throw new RuntimeException("Solo el autor puede modificar este artículo");
        }
        if (article.getStatus() != ArticleStatus.DRAFT) {
            throw new RuntimeException("Solo los artículos en estado DRAFT pueden modificarse");
        }
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        if (dto.getPublishDate() != null) {
            article.setPublishDate(dto.getPublishDate());
        }
        if (dto.getImageUrl() != null) {
            article.setImageUrl(dto.getImageUrl());
        }
        return articleMapper.toResponseDTO(articleRepository.save(article));
    }

    @Override
    public void deleteById(Long id, Long authorId) {
        Article article = findArticleOrThrow(id);
        if (!article.getAuthor().getId().equals(authorId)) {
            throw new RuntimeException("Solo el autor puede eliminar este artículo");
        }
        articleRepository.deleteById(id);
    }

    @Override
    public ArticleResponseDTO submitForReview(Long id, Long authorId) {
        Article article = findArticleOrThrow(id);
        if (!article.getAuthor().getId().equals(authorId)) {
            throw new RuntimeException("Solo el autor puede enviar a revisión");
        }
        if (article.getStatus() != ArticleStatus.DRAFT) {
            throw new RuntimeException("Solo los artículos en estado DRAFT pueden enviarse a revisión");
        }
        article.setStatus(ArticleStatus.IN_REVIEW);
        return articleMapper.toResponseDTO(articleRepository.save(article));
    }

    @Override
    public ArticleResponseDTO approve(Long id) {
        Article article = findArticleOrThrow(id);
        if (article.getStatus() != ArticleStatus.IN_REVIEW) {
            throw new RuntimeException("Solo los artículos en estado IN_REVIEW pueden aprobarse");
        }
        article.setStatus(ArticleStatus.PUBLISHED);
        return articleMapper.toResponseDTO(articleRepository.save(article));
    }

    @Override
    public ArticleResponseDTO reject(Long id) {
        Article article = findArticleOrThrow(id);
        if (article.getStatus() != ArticleStatus.IN_REVIEW) {
            throw new RuntimeException("Solo los artículos en estado IN_REVIEW pueden rechazarse");
        }
        article.setStatus(ArticleStatus.DRAFT);
        return articleMapper.toResponseDTO(articleRepository.save(article));
    }

    private Article findArticleOrThrow(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El artículo con ID " + id + " no existe"));
    }

}
