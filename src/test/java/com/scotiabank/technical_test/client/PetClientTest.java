package com.scotiabank.technical_test.client;

import com.scotiabank.technical_test.dto.external.PetRequestExternalDto;
import com.scotiabank.technical_test.dto.external.PetResponseExternalDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PetClientTest {

    @Mock
    private PetClient petClient;

    @Test
    void testGetPetById() {
        // Arrange
        Integer petId = 1;
        PetResponseExternalDto mockResponse = new PetResponseExternalDto();
        mockResponse.setId(petId);
        mockResponse.setName("Rex");

        when(petClient.getPetById(petId)).thenReturn(mockResponse);

        // Act
        PetResponseExternalDto response = petClient.getPetById(petId);

        // Assert
        assertNotNull(response);
        assertEquals(petId, response.getId());
        assertEquals("Rex", response.getName());

        verify(petClient, times(1)).getPetById(petId);
    }

    @Test
    void testAddPet() {
        // Arrange
        PetRequestExternalDto petRequest = new PetRequestExternalDto();
        petRequest.setName("Rex");
        petRequest.setStatus("available");

        doNothing().when(petClient).addPet(petRequest);

        // Act
        petClient.addPet(petRequest);

        // Assert
        verify(petClient, times(1)).addPet(petRequest);
    }
}