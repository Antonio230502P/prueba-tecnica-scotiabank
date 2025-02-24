package com.scotiabank.technical_test.mapper;

import com.scotiabank.technical_test.dto.PetResponseDto;
import com.scotiabank.technical_test.dto.external.PetResponseExternalDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PetMapper {
    PetResponseDto petResponseExternalDtopetResponseDto(PetResponseExternalDto petResponseExternalDto);
}
