package com.periodico.mundotech.controller;

//import java.io.IOException;
//import java.nio.file.Files;
import java.util.List;

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

import com.periodico.mundotech.dto.request.ArticleRequestDTO;
import com.periodico.mundotech.dto.response.ArticleResponseDTO;
import com.periodico.mundotech.entity.enums.ArticleStatus;
import com.periodico.mundotech.service.ArticleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {
    
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService){
        this.articleService=articleService;
    }
// @PostConstruct
//     public void init() {
//         try {
//             Files.createDirectories(uploadDir);
//         } catch (IOException e) {
//             throw new RuntimeException("No se pudo crear el directorio de uploads", e);
//         }
//     }

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
        return ResponseEntity.ok(articleService.findByIdAuthorAndStatus(authorId, status));
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
    public ResponseEntity<ArticleResponseDTO> approve(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.approve(id));
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<ArticleResponseDTO> reject(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.reject(id));
    }

    // @PostMapping("/upload")
    // public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file) {
    //     if (file.isEmpty()) {
    //         throw new RuntimeException("El archivo no puede estar vacÃ­o");
    //     }
    //     String originalFilename = file.getOriginalFilename();
    //     String extension = "";
    //     if (originalFilename != null && originalFilename.contains(".")) {
    //         extension = originalFilename.substring(originalFilename.lastIndexOf("."));
    //     }
    //     String filename = UUID.randomUUID().toString() + extension;
    //     try {
    //         file.transferTo(uploadDir.resolve(filename).toFile());
    //     } catch (IOException e) {
    //         throw new RuntimeException("No se pudo guardar el archivo", e);
    //     }
    //     return new ResponseEntity<>(Map.of("imageUrl", "/uploads/" + filename), HttpStatus.CREATED);

}
