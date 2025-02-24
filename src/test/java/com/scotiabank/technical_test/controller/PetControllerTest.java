package com.scotiabank.technical_test.controller;

import com.scotiabank.technical_test.dto.PetRequestPdo;
import com.scotiabank.technical_test.dto.PetResponseGetDto;
import com.scotiabank.technical_test.dto.PetResponsePostDto;
import com.scotiabank.technical_test.service.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PetControllerTest {

    @Mock
    private PetService petService;

    @InjectMocks
    private PetController petController;

    private PetRequestPdo petRequestPdo;
    private PetResponseGetDto petResponseGetDto;
    private PetResponsePostDto petResponsePostDto;

    @BeforeEach
    void setUp() {
        // Inicializar objetos DTO para usar en las pruebas
        petRequestPdo = new PetRequestPdo();
        petRequestPdo.setName("Rex");
        petRequestPdo.setStatus("available");

        petResponseGetDto = new PetResponseGetDto();
        petResponseGetDto.setId(1);
        petResponseGetDto.setName("Rex");

        petResponsePostDto = new PetResponsePostDto();
        UUID transactionId = UUID.randomUUID();
        petResponsePostDto.setTransactionId(transactionId);
        petResponsePostDto.setName("Rex");
        petResponsePostDto.setStatus("available");
    }

    @Test
    void testGetPetById() {
        // Arrange
        Integer petId = 1;
        when(petService.getPetById(petId)).thenReturn(petResponseGetDto);

        // Act
        ResponseEntity<PetResponseGetDto> response = petController.getPetById(petId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(petResponseGetDto, response.getBody());

        verify(petService, times(1)).getPetById(petId);
    }

    @Test
    void testAddPet() {
        // Arrange
        when(petService.addPet(petRequestPdo)).thenReturn(petResponsePostDto);

        // Act
        ResponseEntity<PetResponsePostDto> response = petController.addPet(petRequestPdo);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(petResponsePostDto, response.getBody());

        verify(petService, times(1)).addPet(petRequestPdo);
    }
}