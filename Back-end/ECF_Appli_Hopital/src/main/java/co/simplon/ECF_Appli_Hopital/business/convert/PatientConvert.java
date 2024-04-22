// Cette classe permet de convertir des objets DTO (Back) en Entity (BDD)
// pour faciliter la manipulation des données dans de l'application

package co.simplon.ECF_Appli_Hopital.business.convert;

import java.util.ArrayList;
import java.util.List;

import co.simplon.ECF_Appli_Hopital.business.dto.PatientDTO;
import co.simplon.ECF_Appli_Hopital.persistence.entity.Patient;

public class PatientConvert {
    private static PatientConvert instance;

    private PatientConvert() {
        // Aucun constructeur public pour une classe Singleton
    }

    // permet d'obtenir l'instance unique de PatientConvert
    public static PatientConvert getInstance() {
        // vérifie si l'instance existe déjà
        if (instance == null) {
            // sinon crée une nouvelle instance de PatientConvert
            instance = new PatientConvert();
        }
        return instance;
    }

    // Convert Patient (Entity) en Patient(DTO)
    public PatientDTO convertToDto(final Patient entity) {
        PatientDTO dto = new PatientDTO();
        dto.setIdPatient(entity.getIdPatient());
        dto.setPrenom(entity.getPrenom());
        dto.setNom(entity.getNom());
        dto.setDateNaissance(entity.getDateNaissance());
        dto.setNumSecu(entity.getNumSecu());
        dto.setAdresse(entity.getAdresse());
        dto.setCp(entity.getCp());
        dto.setCommune(entity.getCommune());
        dto.setTelephone(entity.getTelephone());
        dto.setEmail(entity.getEmail());
        dto.setDateCreation(entity.getDateCreation());
        dto.setDateModif(entity.getDateModif());

        return dto;
    }

    // Convert Patient (DTO) à Patient(Entity)
    public Patient convertToEntity(final PatientDTO dto) {
        Patient entity = new Patient();
        entity.setIdPatient(dto.getIdPatient());
        entity.setPrenom(dto.getPrenom());
        entity.setNom(dto.getNom());
        entity.setDateNaissance(dto.getDateNaissance());
        entity.setNumSecu(dto.getNumSecu());
        entity.setAdresse(dto.getAdresse());
        entity.setCp(dto.getCp());
        entity.setCommune(dto.getCommune());
        entity.setTelephone(dto.getTelephone());
        entity.setEmail(dto.getEmail());
        entity.setDateCreation(dto.getDateCreation());
        entity.setDateModif(dto.getDateModif());

        return entity;
    }

    // Même chose pour une liste

    // Convert liste Patient (Entity) en liste Patient (DTO)
    public List<PatientDTO> convertListEntityToListDTO(final List<Patient> listPatientEntity) {
        List<PatientDTO> listPatientDTO = new ArrayList<>();
        for (final Patient p : listPatientEntity) {
            listPatientDTO.add(convertToDto(p));
        }
        return listPatientDTO;
    }

    // Convert liste Patient (DTO) en liste Patient (Entity)
    public List<Patient> convertListDTOToListEntity(final List<PatientDTO> listePatients) {
        List<Patient> listPatientEntity = new ArrayList<>();
        for (final PatientDTO pdto : listePatients) {
            listPatientEntity.add(convertToEntity(pdto));
        }
        return listPatientEntity;
    }
}
