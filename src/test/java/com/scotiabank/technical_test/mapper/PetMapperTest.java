package com.scotiabank.technical_test.mapper;

import com.scotiabank.technical_test.dto.PetRequestPdo;
import com.scotiabank.technical_test.dto.PetResponseGetDto;
import com.scotiabank.technical_test.dto.PetResponsePostDto;
import com.scotiabank.technical_test.dto.external.PetRequestExternalDto;
import com.scotiabank.technical_test.dto.external.PetResponseExternalDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetMapperTest {
    @InjectMocks
    private PetMapperImpl petMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPetResponseExternalDtoToPetResponseGetDto() {
        PetResponseExternalDto externalDto = new PetResponseExternalDto();
        externalDto.setId(1);
        externalDto.setName("Fido");
        externalDto.setStatus("available");

        PetResponseGetDto getDto = petMapper.petResponseExternalDtoPetResponseDto(externalDto);

        assertEquals(1, getDto.getId());
        assertEquals("Fido", getDto.getName());
        assertEquals("available", getDto.getStatus());
    }

    @Test
    public void testPetRequestDtoToPetRequestExternalDto() {
        PetRequestPdo requestPdo = new PetRequestPdo();
        requestPdo.setName("Fido");

        PetRequestExternalDto externalDto = petMapper.petRequestDtoToPetRequestExternalDto(requestPdo);

        assertEquals("Fido", externalDto.getName());
    }

    @Test
    public void testPetRequestExternalDtoToPetResponsePostDto() {
        PetRequestExternalDto externalDto = new PetRequestExternalDto();
        externalDto.setName("Fido");
        externalDto.setStatus("available");

        PetResponsePostDto postDto = petMapper.petRequestExternalDtoToPetResponsePostDto(externalDto);

        assertEquals("Fido", postDto.getName());
        assertEquals("available", postDto.getStatus());
    }
}
