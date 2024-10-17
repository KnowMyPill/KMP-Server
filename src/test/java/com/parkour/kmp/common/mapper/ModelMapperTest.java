package com.parkour.kmp.common.mapper;
import com.parkour.kmp.api.medication.domain.Medication;
import com.parkour.kmp.api.medication.payload.response.MedicationResponse;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelMapperTest {

    private final ModelMapper modelMapper;

    public ModelMapperTest() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldAccessLevel(AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);
    }

    @Test
    public void testMappingMedicationResponseToMedication() {
        MedicationResponse response = new MedicationResponse(
                "itemSeq123",
                "Test Medication",
                "Helps with headaches",
                "Take one pill daily",
                "Avoid alcohol",
                "No known warnings",
                "Not applicable",
                "Nausea",
                "Store in a cool place"
        );

        Medication medication = modelMapper.map(response, Medication.class);

        assertEquals("itemSeq123", medication.getItemSeq());
    }
}

