package com.scotiabank.technical_test.controller;

import com.scotiabank.technical_test.dto.PetResponseDto;
import com.scotiabank.technical_test.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pet")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetResponseDto> getPetById(@PathVariable Integer petId){
        return ResponseEntity.ok(this.petService.getPetById(petId));
    }
}
