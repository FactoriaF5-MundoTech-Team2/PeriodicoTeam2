package com.periodico.mundotech.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService { 
    String uploadImage(Long articleId, MultipartFile file)
    throws IOException;

}
