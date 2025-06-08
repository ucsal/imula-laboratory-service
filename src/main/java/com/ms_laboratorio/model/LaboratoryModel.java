package com.ms_laboratorio.model;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "laboratory_tb")
public class LaboratoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long laboratoryId;
    private String laboratoryName;
    private Boolean laboratoryAvailability;

    //@ManyToMany(mappedBy = "laboratoriesList")
    @JsonIgnore
    private Set<UUID> softwaresInstalled;

    public LaboratoryModel() {
        softwaresInstalled = new LinkedHashSet<>();
    }

    public LaboratoryModel(String laboratoryName, Boolean laboratoryAvailability, Set<UUID> softwaresInstalled) {
        this.laboratoryName = laboratoryName;
        this.laboratoryAvailability = laboratoryAvailability;
        this.softwaresInstalled = softwaresInstalled;
    }

    public Long getLaboratoryId() {
        return laboratoryId;
    }

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
    }

    public Boolean getLaboratoryAvailability() {
        return laboratoryAvailability;
    }

    public void setLaboratoryAvailability(Boolean laboratoryAvailability) {
        this.laboratoryAvailability = laboratoryAvailability;
    }

    public Set<UUID> getSoftwaresInstalled() {
        return softwaresInstalled;
    }

    public void setSoftwaresInstalled(Set<UUID> listSoftware) {
        this.softwaresInstalled = listSoftware;
    }
}
