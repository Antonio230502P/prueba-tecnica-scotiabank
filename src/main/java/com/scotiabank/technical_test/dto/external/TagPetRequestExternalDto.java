package com.scotiabank.technical_test.dto.external;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class TagPetRequestExternalDto {
    private Integer id;
    private String name;
}
