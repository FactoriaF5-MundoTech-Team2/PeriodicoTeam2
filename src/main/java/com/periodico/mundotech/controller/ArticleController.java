package com.periodico.mundotech.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.periodico.mundotech.dto.request.ArticleRequestDTO;
import com.periodico.mundotech.dto.response.ArticleResponseDTO;
import com.periodico.mundotech.entity.enums.ArticleStatus;
import com.periodico.mundotech.service.ArticleService;
import com.periodico.mundotech.service.StorageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {
    
    private final ArticleService articleService;
    private final StorageService storageService;

    public ArticleController(ArticleService articleService, StorageService storageService){
        this.articleService=articleService;
        this.storageService=storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("articleId") Long articleId, 
@RequestParam("file") MultipartFile file) throws IOException{
        String filePath=storageService.uploadImage(articleId, file);
        
        return new ResponseEntity<>(Map.of("imageUrl", filePath), HttpStatus.CREATED);
    }
    

    @PostMapping
    public ResponseEntity<ArticleResponseDTO> createArticle(@Valid @RequestBody ArticleRequestDTO dto) {
        return new ResponseEntity<>(articleService.createArticle(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ArticleResponseDTO>> findAll() {
        return ResponseEntity.ok(articleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.findById(id));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<ArticleResponseDTO>> findByIdAuthor(@PathVariable Long authorId) {
        return ResponseEntity.ok(articleService.findByIdAuthor(authorId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ArticleResponseDTO>> findByStatus(@PathVariable ArticleStatus status) {
        return ResponseEntity.ok(articleService.findByStatus(status));
    }

    @GetMapping("/my-articles")
    public ResponseEntity<List<ArticleResponseDTO>> findByIdAuthorAndStatus(
            @RequestParam Long authorId,
            @RequestParam ArticleStatus status) {
        return ResponseEntity.ok(articleService.findByAuthorIdAndStatus(authorId, status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponseDTO> updateArticle(
            @PathVariable Long id,
            @Valid @RequestBody ArticleRequestDTO dto,
            @RequestParam Long authorId) {
        return ResponseEntity.ok(articleService.updateArticle(id, dto, authorId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id, @RequestParam Long authorId) {
        articleService.deleteById(id, authorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/submit-review")
    public ResponseEntity<ArticleResponseDTO> submitForReview(
            @PathVariable Long id,
            @RequestParam Long authorId) {
        return ResponseEntity.ok(articleService.submitForReview(id, authorId));
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<ArticleResponseDTO> approve(
        @PathVariable Long id,
        @RequestParam Long managerId) {
        return ResponseEntity.ok(articleService.approve(id, managerId));
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<ArticleResponseDTO> reject(
        @PathVariable Long id,
        @RequestParam Long managerId) {
        return ResponseEntity.ok(articleService.reject(id, managerId));
    }
}
