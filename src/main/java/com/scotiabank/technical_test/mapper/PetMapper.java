package com.scotiabank.technical_test.mapper;

import com.scotiabank.technical_test.dto.PetRequestPdo;
import com.scotiabank.technical_test.dto.PetResponseGetDto;
import com.scotiabank.technical_test.dto.PetResponsePostDto;
import com.scotiabank.technical_test.dto.external.PetRequestExternalDto;
import com.scotiabank.technical_test.dto.external.PetResponseExternalDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PetMapper {
    PetResponseGetDto petResponseExternalDtoPetResponseDto(PetResponseExternalDto petResponseExternalDto);

    PetRequestExternalDto petRequestDtoToPetRequestExternalDto(PetRequestPdo petRequestPdo);

    PetResponsePostDto petRequestExternalDtoToPetResponsePostDto(PetRequestExternalDto pet);
}
