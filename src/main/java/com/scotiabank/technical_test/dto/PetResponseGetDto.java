package com.scotiabank.technical_test.dto;

import lombok.Data;

@Data
public class PetResponseGetDto {
    private Integer id;
    private String name;
    private String status;
}
