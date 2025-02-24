package com.scotiabank.technical_test.controller;

import com.scotiabank.technical_test.dto.PetRequestPdo;
import com.scotiabank.technical_test.dto.PetResponseGetDto;
import com.scotiabank.technical_test.dto.PetResponsePostDto;
import com.scotiabank.technical_test.service.PetService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/pet")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetResponseGetDto> getPetById(@PathVariable Integer petId){
        return ResponseEntity.ok(this.petService.getPetById(petId));
    }

    @PostMapping
    public ResponseEntity<PetResponsePostDto> addPet(@Valid @RequestBody PetRequestPdo petRequestPdo){
        return new ResponseEntity<>(this.petService.addPet(petRequestPdo), CREATED);
    }
}
