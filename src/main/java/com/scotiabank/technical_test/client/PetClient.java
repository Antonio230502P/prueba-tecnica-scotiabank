package com.scotiabank.technical_test.client;

import com.scotiabank.technical_test.dto.external.PetResponseExternalDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "petClient", url = "https://petstore.swagger.io/v2")
public interface PetClient {
    @GetMapping("/pet/{petId}")
    PetResponseExternalDto getPetById(@PathVariable("petId") Integer petId);
}
