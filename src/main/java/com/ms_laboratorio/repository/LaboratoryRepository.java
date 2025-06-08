package com.ms_laboratorio.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms_laboratorio.model.LaboratoryModel;

@Repository
public interface LaboratoryRepository extends JpaRepository<LaboratoryModel, Long> {

    //Set<LaboratoryModel> findBySoftwaresInstalled_SoftwareId(UUID softwareId);

}