package co.simplon.ECF_Appli_Hopital.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.simplon.ECF_Appli_Hopital.persistence.entity.Patient;

// l'Interface hérite de la classe Patient
public interface PatientQuery extends JpaRepository<Patient, Long> {

}
