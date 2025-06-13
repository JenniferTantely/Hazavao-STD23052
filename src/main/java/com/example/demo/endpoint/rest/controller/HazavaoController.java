package com.example.demo.endpoint.rest.controller;

import com.example.demo.service.HazavaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HazavaoController {
    private final HazavaoService hazavaoService;

    @GetMapping("/hazavao")
    public ResponseEntity<String> hazavao(@RequestParam String teny) {
        String definition = hazavaoService.getDefinition(teny);
        return ResponseEntity.ok(definition);
    }
}
