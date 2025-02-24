package com.scotiabank.technical_test.service;

import com.scotiabank.technical_test.dto.PetRequestPdo;
import com.scotiabank.technical_test.dto.PetResponseGetDto;
import com.scotiabank.technical_test.dto.PetResponsePostDto;

public interface PetService {
    PetResponseGetDto getPetById(Integer id);

    PetResponsePostDto addPet(PetRequestPdo petRequestPdo);
}
