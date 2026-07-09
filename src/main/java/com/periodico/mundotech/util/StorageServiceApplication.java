package com.periodico.mundotech.util;

import org.springframework.web.bind.annotation.PostMapping;

@Autowired
private StorageService service;

@PostMapping
public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
    String uploadImage = service.uploadImage(file);
    return ResponseEntity.status(HttpStatus.OK)
    .body(uploadImage);
    
}
