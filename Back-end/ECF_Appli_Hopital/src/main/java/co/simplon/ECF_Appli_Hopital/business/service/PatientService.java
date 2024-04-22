package co.simplon.ECF_Appli_Hopital.business.service;

import java.util.List;

import co.simplon.ECF_Appli_Hopital.business.dto.PatientDTO;

public interface PatientService {
    PatientDTO ajouterPatient(PatientDTO patient);

    List<PatientDTO> afficherListePatients();

    PatientDTO afficherPatient(Long id);

    PatientDTO modifierPatient(PatientDTO patient, Long id);
}
