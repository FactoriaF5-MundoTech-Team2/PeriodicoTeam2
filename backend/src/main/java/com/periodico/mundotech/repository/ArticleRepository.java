package com.periodico.mundotech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.periodico.mundotech.entity.Article;
import com.periodico.mundotech.entity.enums.ArticleStatus;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByAuthorId(Long authorId);
    List<Article> findByStatus(ArticleStatus status);
    List<Article> findByIdAuthorAndStatus(Long authorId, ArticleStatus status);

}
