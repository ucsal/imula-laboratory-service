package com.ms_laboratorio.dto.response;



import java.util.Set;
import java.util.UUID;

import com.ms_laboratorio.model.LaboratoryModel;

public record LaboratoryResponse(
        Long laboratoryId,
        String laboratoryName,
        Boolean laboratoryAvailability,
        Set<UUID> softwaresInstalled
        ) {
    public static LaboratoryResponse parseToLaboratoryResponse(LaboratoryModel laboratoryModel) {
        return new LaboratoryResponse(
                laboratoryModel.getLaboratoryId(),
                laboratoryModel.getLaboratoryName(),
                laboratoryModel.getLaboratoryAvailability(),
                /*laboratoryModel.getSoftwaresInstalled()
                        .stream()
                        .map(software -> new SoftwareSimpleResponse(
                                software.getSoftwareId(),
                                software.getSoftwareName(),
                                software.getSoftwareDescription(),
                                software.getSoftwareVersion(),
                                software.getSoftwareAuthor(),
                                software.getSoftwareLink(),
                                software.getLicenseModel(),
                                software.getRegistrationDate(),
                                software.isAvailable()))
                        .collect(Collectors.toSet())*/
                laboratoryModel.getSoftwaresInstalled()
        );
    }
    public static LaboratoryResponse parseToLaboratoryResponse(
            LaboratoryModel laboratoryModel,
            Set<UUID> softwares) {
        return new LaboratoryResponse(
                laboratoryModel.getLaboratoryId(),
                laboratoryModel.getLaboratoryName(),
                laboratoryModel.getLaboratoryAvailability(),
                // softwares.stream()
                //         .map(software -> new SoftwareSimpleResponse(
                //                 software.getSoftwareId(),
                //                 software.getSoftwareName(),
                //                 software.getSoftwareDescription(),
                //                 software.getSoftwareVersion(),
                //                 software.getSoftwareAuthor(),
                //                 software.getSoftwareLink(),
                //                 software.getLicenseModel(),
                //                 software.getRegistrationDate(),
                //                 software.isAvailable()))
                //         .collect(Collectors.toSet())
                softwares
        );
    }


}
