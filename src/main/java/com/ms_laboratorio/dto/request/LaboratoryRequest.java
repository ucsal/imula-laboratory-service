package com.ms_laboratorio.dto.request;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import com.ms_laboratorio.model.LaboratoryModel;

public record LaboratoryRequest(
        
        String laboratoryName,
        Boolean laboratoryAvailability,
        Set<UUID> softwaresInstalled
) {
    public LaboratoryModel toLaboratoryModel(LaboratoryRequest laboratoryRequest) {
        return new LaboratoryModel(
                laboratoryRequest.laboratoryName(),
                laboratoryRequest.laboratoryAvailability(),
                new LinkedHashSet<>()
        );
    }
}
