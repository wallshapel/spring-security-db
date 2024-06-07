package com.app.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()") // Funciona si en la configuración de spring security está la anotación @EnableMethodSecurity. No deja pasar a nadie
public class TestAuthController {

    @Transactional(readOnly = true)
    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public String helloGet() {
        return "Hello world - GET";
    }

    @Transactional()
    @PostMapping("/post")
    @PreAuthorize("hasAuthority('READ') or hasAuthority('CREATE')") // Esta es la forma de establecer más condiciones de permisos y roles para acceder al endpoint
    public String helloPost() {
        return "Hello world - POST";
    }

    @Transactional()
    @PutMapping("/put")
    public String helloPut() {
        return "Hello world - PUT";
    }

    @Transactional()
    @DeleteMapping("/delete")
    public String helloDelete() {
        return "Hello world - DELETE";
    }

    @Transactional()
    @PatchMapping("/patch")
    @PreAuthorize("hasAuthority('REFACTOR')")
    public String helloPatch() {
        return "Hello world - PATCH";
    }

}
