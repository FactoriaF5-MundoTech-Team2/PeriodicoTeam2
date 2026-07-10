package com.periodico.mundotech.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.periodico.mundotech.service.StorageService;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/v1/images")
public class StorageController {
    private final StorageService storageService;
    public StorageController(StorageService storageService){
        this.storageService=storageService;
    }
    @PostMapping("/{articleId}")
    public ResponseEntity<String> uploadImage(
        @PathVariable Long articleId,
        @RequestParam("file") MultipartFile file) throws IOException{
        String filePath = storageService.uploadImage(articleId, file);
        return ResponseEntity.ok("Imagen subida correctamente: "+filePath);
    }
    
}
