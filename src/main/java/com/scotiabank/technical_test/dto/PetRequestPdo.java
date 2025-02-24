package com.scotiabank.technical_test.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PetRequestPdo {
    @Min(value = 1, message = "El id de la mascota no puede ser menor a 1")
    @NotNull(message = "El id de la mascota no puede ser nulo")
    private Integer id;

    @NotBlank(message = "El campo 'status' no puede ir en blanco ni ser nulo")
    private String status;

    @NotBlank(message = "El campo de 'name' no puede ir en blanco ni ser nulo")
    private String name;
}
