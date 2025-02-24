package com.scotiabank.technical_test.dto.external;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PetRequestExternalDto {
    private Integer id;
    private CategoryPetRequestExternalDto category = new CategoryPetRequestExternalDto();
    private String name;
    private ArrayList<String> photoUrls = new ArrayList<>();
    private ArrayList<TagPetRequestExternalDto> tags = new ArrayList<>();
    private String status;
}
