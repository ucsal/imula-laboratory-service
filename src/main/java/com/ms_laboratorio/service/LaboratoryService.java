package com.ms_laboratorio.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.ms_laboratorio.dto.request.LaboratoryRequest;
import com.ms_laboratorio.dto.response.LaboratoryResponse;
import com.ms_laboratorio.dto.response.SoftwareResponse;
import com.ms_laboratorio.model.LaboratoryModel;
import com.ms_laboratorio.repository.LaboratoryRepository;

@Service
public class LaboratoryService {

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @Autowired
    private WebClient.Builder webClient;

    // @Autowired
    // private SoftwareService softwareService;

    public List<LaboratoryResponse> getAllLaboratories() {
        List<LaboratoryModel> listLaboratories = laboratoryRepository.findAll();
        return listLaboratories.stream()
                .map(LaboratoryResponse::parseToLaboratoryResponse)
                .collect(Collectors.toList());
    }

    public LaboratoryResponse getLaboratoryById(Long laboratoryId) {
        LaboratoryModel laboratoryRegistred = laboratoryRepository.findById(laboratoryId).get();
        // Set<SoftwareModel> softwaresAssociates =
        // softwareService.getSoftwaresByLaboratoryId(laboratoryId);
        // return LaboratoryResponse.parseToLaboratoryResponse(laboratoryRegistred,
        // softwaresAssociates);
        return LaboratoryResponse.parseToLaboratoryResponse(laboratoryRegistred);

    }

    public LaboratoryModel findLaboratoryById(Long laboratoryId) {
        return laboratoryRepository.findById(laboratoryId).get();
    }

    // Metodo que retorna uma lista (Set) de todos os laboratorios que possuem um
    // determinado software instalado
    // public Set<LaboratoryModel> getLaboratoriesBySoftwareId(UUID softwareId) {
    // return laboratoryRepository.findBySoftwaresInstalled_SoftwareId(softwareId);
    // }

    @Transactional
    public LaboratoryResponse createLaboratory(LaboratoryRequest laboratoryRequest) {

        LaboratoryModel newLaboratory = laboratoryRequest.toLaboratoryModel(laboratoryRequest);
        laboratoryRepository.save(newLaboratory);
        return LaboratoryResponse.parseToLaboratoryResponse(newLaboratory);
    }

    public void updateLaboratory(Long laboratoryId, LaboratoryRequest laboratoryRequest) {
        LaboratoryModel laboratoryRegistred = laboratoryRepository.findById(laboratoryId).get();
        processUpdate(laboratoryRequest, laboratoryRegistred);
        laboratoryRegistred.setSoftwaresInstalled(laboratoryRequest.softwaresInstalled());
        laboratoryRepository.save(laboratoryRegistred);
        sendLaboratory(LaboratoryResponse.parseToLaboratoryResponse(laboratoryRegistred));
    }

    private static LaboratoryModel processUpdate(LaboratoryRequest laboratoryRequest,
            LaboratoryModel laboratoryRegistred) {
        laboratoryRegistred.setLaboratoryName(laboratoryRequest.laboratoryName());
        laboratoryRegistred.setLaboratoryAvailability(laboratoryRequest.laboratoryAvailability());

        return laboratoryRegistred;
    }

    @Transactional
    public void removeSoftware(Long laboratoryId, UUID softwareId) {
        LaboratoryModel laboratoryRegistred = laboratoryRepository.findById(laboratoryId)
                .orElseThrow(() -> new NoSuchElementException("Laboratório não encontrado"));
        // SoftwareModel software = softwareService.findSoftwareBySoftwarId(softwareId);
        SoftwareResponse software = findSoftwaresByUUID(softwareId);
        laboratoryRegistred.getSoftwaresInstalled().remove(software.softwareId());
    }

    public SoftwareResponse findSoftwaresByUUID(UUID softwareId) {
        return webClient
                .build()
                .get()
                .uri("http://ms-software/software/{id}", softwareId)
                .retrieve()
                .bodyToMono(SoftwareResponse.class)
                .block();
    }

    public LaboratoryResponse sendLaboratory(LaboratoryResponse laboratory) {
        return webClient
                .build()
                .put()
                .uri("http://ms-software/software")
                .bodyValue(laboratory)
                .retrieve()
                .bodyToMono(LaboratoryResponse.class)
                .block();
    }

}
