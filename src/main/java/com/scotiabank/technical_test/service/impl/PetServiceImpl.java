package com.scotiabank.technical_test.service.impl;

import com.scotiabank.technical_test.client.PetClient;
import com.scotiabank.technical_test.dto.PetResponseDto;
import com.scotiabank.technical_test.dto.external.PetResponseExternalDto;
import com.scotiabank.technical_test.mapper.PetMapper;
import com.scotiabank.technical_test.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PetServiceImpl implements PetService {
    final PetClient petClient;
    final PetMapper petMapper;

    @Autowired
    public PetServiceImpl(PetClient petClient, PetMapper petMapper) {
        this.petClient = petClient;
        this.petMapper = petMapper;
    }

    @Override
    public PetResponseDto getPetById(Integer id) {
        PetResponseExternalDto petResponseExternalDto = petClient.getPetById(id);
        log.info(petResponseExternalDto.toString());
        return this.petMapper.petResponseExternalDtopetResponseDto(petResponseExternalDto);
    }
}
