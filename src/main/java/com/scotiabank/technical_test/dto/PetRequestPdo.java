package com.scotiabank.technical_test.dto;

import lombok.Data;

@Data
public class PetRequestPdo {
    private Integer id;
    private String status;
    private String name;
}
