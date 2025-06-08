package com.ms_laboratorio.controller;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ms_laboratorio.dto.request.LaboratoryRequest;
import com.ms_laboratorio.dto.response.LaboratoryResponse;
import com.ms_laboratorio.service.LaboratoryService;

@RestController
@RequestMapping("/laboratory")
//PERFIL: ADMIN
public class LaboratoryController {

    @Autowired
    private LaboratoryService laboratoryService;

    //@PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'USER')")
    @GetMapping()
    public ResponseEntity<List<LaboratoryResponse>> getAllLaboratory() {
        List<LaboratoryResponse> laboratoriesListResponse = laboratoryService.getAllLaboratories();
        return ResponseEntity.status(HttpStatus.OK).body(laboratoriesListResponse);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    //@GetMapping("/{laboratoryId}")
    //public ResponseEntity<LaboratoryResponse> softwaresAssociatesByLaboratory(@PathVariable Long laboratoryId) {
        //LaboratoryResponse laboratoryResponse = laboratoryService.getLaboratoryById(laboratoryId);
        //return ResponseEntity.status(HttpStatus.OK).body(laboratoryResponse);
    //}

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<Void> registerLaboratory(@RequestBody LaboratoryRequest laboratoryRequest) {
        LaboratoryResponse responseNewSoftware = laboratoryService.createLaboratory(laboratoryRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseNewSoftware.laboratoryId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    //@PreAuthorize("hasRole('ADMIN')")
    /*@PutMapping("/{laboratoryId}")
    public ResponseEntity<Void> updateLaboratory(
            @PathVariable Long laboratoryId,
            @RequestBody 
            LaboratoryRequest laboratoryRequest) {
        laboratoryService.updateLaboratory(laboratoryId, laboratoryRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }*/

    //@PreAuthorize("hasRole('ADMIN')")
    /*@DeleteMapping("/{laboratoryId}")
    public ResponseEntity<Void> deleteLaboratory(@PathVariable Long laboratoryId, @RequestBody UUID softwareId) {
        laboratoryService.removeSoftware(laboratoryId, softwareId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }*/
}
