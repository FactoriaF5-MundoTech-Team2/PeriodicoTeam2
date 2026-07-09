package com.periodico.mundotech.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.periodico.mundotech.dto.request.RoleRequestDTO;
import com.periodico.mundotech.dto.response.RoleResponseDTO;
import com.periodico.mundotech.service.RoleService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService=roleService;
    }

    @PostMapping
    public ResponseEntity<RoleResponseDTO> createRole(@Valid @RequestBody RoleRequestDTO dto){
        return new ResponseEntity<>(roleService.createRole(dto),HttpStatus.CREATED);
    }
    
}
