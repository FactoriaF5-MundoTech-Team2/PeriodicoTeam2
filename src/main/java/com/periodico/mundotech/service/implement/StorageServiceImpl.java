package com.periodico.mundotech.service.implement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.periodico.mundotech.entity.Article;
import com.periodico.mundotech.entity.FileData;
import com.periodico.mundotech.repository.ArticleRepository;
import com.periodico.mundotech.service.StorageService;

import jakarta.persistence.criteria.Path;
import lombok.Value;

@Service
public class StorageServiceImpl implements StorageService {
    private final ArticleRepository articleRepository;

    @Value("${app.upload.dir}")
    private String uploadDir;

    public StorageServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public String uploadImage(Long articleId, MultipartFile file) throws IOException {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("ArtÃculo no encontrado con ID: " + articleId));

        Path uploadPath = Path.of(uploadDir);
        Files.createDirectories(uploadPath);

        String extension = "";
        String originalName = file.getOriginalFilename();
        if (originalName != null && originalName.contains(".")) {
            extension = originalName.substring(originalName.lastIndexOf("."));
        }
        String filename = UUID.randomUUID() + extension;
        Path filePath = uploadPath.resolve(filename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        FileData fileData = FileData.builder()
                .name(filename)
                .type(file.getContentType())
                .filePath(filePath.toString())
                .build();

        article.setImage(fileData);
        articleRepository.save(article);

        return filePath.toString();
    }
    
}
