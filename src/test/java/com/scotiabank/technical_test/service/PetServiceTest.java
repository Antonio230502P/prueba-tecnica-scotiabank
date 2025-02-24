package com.scotiabank.technical_test.service;

import com.scotiabank.technical_test.client.PetClient;
import com.scotiabank.technical_test.dto.PetRequestPdo;
import com.scotiabank.technical_test.dto.PetResponseGetDto;
import com.scotiabank.technical_test.dto.PetResponsePostDto;
import com.scotiabank.technical_test.dto.external.CategoryPetRequestExternalDto;
import com.scotiabank.technical_test.dto.external.PetRequestExternalDto;
import com.scotiabank.technical_test.dto.external.PetResponseExternalDto;
import com.scotiabank.technical_test.dto.external.TagPetRequestExternalDto;
import com.scotiabank.technical_test.mapper.PetMapper;
import com.scotiabank.technical_test.service.impl.PetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PetServiceTest {

    @InjectMocks
    private PetServiceImpl petService;

    @Mock
    private PetClient petClient;

    @Mock
    private PetMapper petMapper;

    @Mock
    private Random random;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPetById() {
        PetResponseExternalDto externalDto = new PetResponseExternalDto();
        externalDto.setId(1);
        externalDto.setName("Fido");
        externalDto.setStatus("available");

        PetResponseGetDto getDto = new PetResponseGetDto();
        getDto.setId(1);
        getDto.setName("Fido");
        getDto.setStatus("available");

        when(petClient.getPetById(1)).thenReturn(externalDto);
        when(petMapper.petResponseExternalDtoPetResponseDto(externalDto)).thenReturn(getDto);

        PetResponseGetDto result = petService.getPetById(1);

        assertEquals(1, result.getId());
        assertEquals("Fido", result.getName());
        assertEquals("available", result.getStatus());
    }

    @Test
    public void testAddPet() {
        PetRequestPdo requestPdo = new PetRequestPdo();
        requestPdo.setName("Fido");

        PetRequestExternalDto requestExternalDto = new PetRequestExternalDto();
        requestExternalDto.setName("Fido");
        CategoryPetRequestExternalDto categoryPetRequestExternalDto = new CategoryPetRequestExternalDto();
        categoryPetRequestExternalDto.setId(1);
        categoryPetRequestExternalDto.setName("Perros");
        requestExternalDto.setCategory(categoryPetRequestExternalDto);
        ArrayList<String> photos = new ArrayList<>();
        photos.add("Foto de Fido.jpg");
        photos.add("Foto de Fido comiendo.png");
        requestExternalDto.setPhotoUrls(photos);
        ArrayList<TagPetRequestExternalDto> tags = new ArrayList<>();
        tags.add(new TagPetRequestExternalDto(1, "Jugueton"));
        requestExternalDto.setTags(tags);

        PetResponsePostDto responsePostDto = new PetResponsePostDto();
        responsePostDto.setName("Fido");
        responsePostDto.setTransactionId(UUID.randomUUID());
        responsePostDto.setDateCreated(LocalDateTime.now());

        when(petMapper.petRequestDtoToPetRequestExternalDto(requestPdo)).thenReturn(requestExternalDto);
        when(random.nextInt(any(int.class))).thenReturn(0);
        when(petMapper.petRequestExternalDtoToPetResponsePostDto(requestExternalDto)).thenReturn(responsePostDto);

        PetResponsePostDto result = petService.addPet(requestPdo);

        assertEquals("Fido", result.getName());
        assertEquals(responsePostDto.getTransactionId(), result.getTransactionId());
        assertEquals(responsePostDto.getDateCreated(), result.getDateCreated());
    }
}
