package com.scotiabank.technical_test.dto.external;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PetResponseExternalDto {
    private Integer id;
    private CategoryPetResponseExternalDto category;
    private String name;
    private ArrayList<String> photoUrls;
    private ArrayList<TagPetResponseExternalDto> tags;
    private String status;
}
