package com.scotiabank.technical_test.service;

import com.scotiabank.technical_test.dto.PetResponseDto;

public interface PetService {
    PetResponseDto getPetById(Integer id);
}
