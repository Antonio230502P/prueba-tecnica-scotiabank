package com.scotiabank.technical_test.service.impl;

import com.scotiabank.technical_test.client.PetClient;
import com.scotiabank.technical_test.dto.PetRequestPdo;
import com.scotiabank.technical_test.dto.PetResponseGetDto;
import com.scotiabank.technical_test.dto.PetResponsePostDto;
import com.scotiabank.technical_test.dto.external.PetRequestExternalDto;
import com.scotiabank.technical_test.dto.external.PetResponseExternalDto;
import com.scotiabank.technical_test.dto.external.TagPetRequestExternalDto;
import com.scotiabank.technical_test.mapper.PetMapper;
import com.scotiabank.technical_test.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
public class PetServiceImpl implements PetService {
    final private PetClient petClient;
    final private PetMapper petMapper;

    final private Random random = new Random();

    @Autowired
    public PetServiceImpl(PetClient petClient, PetMapper petMapper) {
        this.petClient = petClient;
        this.petMapper = petMapper;
    }

    @Override
    public PetResponseGetDto getPetById(Integer id) {
        PetResponseExternalDto petResponseExternalDto = petClient.getPetById(id);
        log.info(petResponseExternalDto.toString());
        return this.petMapper.petResponseExternalDtoPetResponseDto(petResponseExternalDto);
    }

    @Override
    public PetResponsePostDto addPet(PetRequestPdo petRequestPdo) {
        PetRequestExternalDto petToAdd = this.petMapper.petRequestDtoToPetRequestExternalDto(petRequestPdo);

        String [] petCategories = {"Gatos", "Perros", "Aves", "Reptiles", "Roedores", "Peces"};
        int categoryId = random.nextInt(petCategories.length);
        petToAdd.getCategory().setId(categoryId + 1);
        petToAdd.getCategory().setName(petCategories[categoryId]);

        petToAdd.getPhotoUrls().add("Foto de " + petRequestPdo.getName() + ".jpg");
        petToAdd.getPhotoUrls().add("Foto de " + petRequestPdo.getName() + " comiendo.png");

        String [] petTags = {"Jugueton", "Tranquilo", "Agresivo"};
        int tagId = random.nextInt(petTags.length);
        petToAdd.getTags().add(new TagPetRequestExternalDto(tagId + 1, petTags[tagId]));

        PetResponsePostDto petResponsePostDto = this.petMapper.petRequestExternalDtoToPetResponsePostDto(petToAdd);
        petResponsePostDto.setTransactionId(UUID.randomUUID());

        petResponsePostDto.setDateCreated(LocalDateTime.now());

        this.petClient.addPet(petToAdd);

        log.info(petRequestPdo.toString());
        return petResponsePostDto;
    }
}
