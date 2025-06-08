package com.ms_laboratorio.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record SoftwareResponse(
        UUID softwareId,
        String softwareName,
        String softwareDescription,
        String softwareVersion,
        String softwareAuthor,
        String softwareLink,
        String licenseModel,
        LocalDate requestDate,
        Boolean availability,
        Boolean softwareInstalled
) {
    
}
