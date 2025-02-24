package com.scotiabank.technical_test.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PetResponsePostDto {
    private UUID transactionId;
    private LocalDateTime dateCreated;
    private String status;
    private String name;
}
