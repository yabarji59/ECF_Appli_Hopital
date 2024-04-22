package co.simplon.ECF_Appli_Hopital.business.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.simplon.ECF_Appli_Hopital.business.convert.PatientConvert;
import co.simplon.ECF_Appli_Hopital.business.dto.PatientDTO;
import co.simplon.ECF_Appli_Hopital.business.exception.PatientIntrouvableException;
import co.simplon.ECF_Appli_Hopital.persistence.entity.Patient;
import co.simplon.ECF_Appli_Hopital.persistence.repository.PatientQuery;

@Service
public class PatientServiceImpl implements PatientService {
    // permet d'interagir avec la base de données pour l'entité Patient (--> Repository)
    private PatientQuery patientRepository;

    // constructeur
    // prend en paramètre PatientQuery, permet d'interagir avec le Repository pour accéder aux donnée des patients
    public PatientServiceImpl(PatientQuery patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    // permet d'ajouter un nouveau patient dans la BDD
    public PatientDTO ajouterPatient(PatientDTO patientDto) {
        // déclaration variable 'entityPatient' pour convertir le patient (à ajouter) de DTO à Entity
        Patient entityPatient = PatientConvert.getInstance().convertToEntity(patientDto);
        // on met à jour la date de création
        entityPatient.setDateCreation(patientDto.getDateCreation());
        // déclation variable 'savePatient' pour sauvegarder le nouveau patient (Entity)
        Patient savePatient = patientRepository.save(entityPatient);

        // et on renvoi le patient sauvegardé converti en DTO
        return PatientConvert.getInstance().convertToDto(savePatient);
    }

    @Override
    // permet d'afficher tous les patient existant de la BDD
    public List<PatientDTO> afficherListePatients() {
        // déclaration variable 'listePatients' pour récupèrer tous les patients de la BDD sous forme de liste
        List<Patient> listePatient = patientRepository.findAll();
        // ici on retourne la liste des entités Patient convertie en DTO
        return PatientConvert.getInstance().convertListEntityToListDTO(listePatient);
    }

    @Override
    public PatientDTO afficherPatient(Long id) {
        // déclaration variable 'patientOptional' pour récupérer un patient précis par son id 
        // 'Optional' permet de récupérer une valeur qui peut être présente ou absente de la BDD avec la méthode 'findById()'
        @SuppressWarnings("null")
        Optional<Patient> patientOptional = patientRepository.findById(id);
        // condition : si le patient recherché par son id est présent dans la BDD
        if (patientOptional.isPresent()) {
            // on le récupère dans la variable déclarée 'patient'
            Patient patientTrouve = patientOptional.get();
            // et on le retourne converti en DTO
            return PatientConvert.getInstance().convertToDto(patientTrouve);
        } else { // dans le cas ou aucun id ne match avec le patient que l'on recherche...
            // ...on retourne 'null'
            return null;
        }
    }

    @Override
    public PatientDTO modifierPatient(PatientDTO patientDto, Long id) {
        // d'abord on recherche le patient comme dans la méthode précédente
        @SuppressWarnings("null")
        Optional<Patient> patientOptional = patientRepository.findById(id);
        // condition : si le patient recherché par son id est présent dans la BDD
        if (patientOptional.isPresent()) {
            // on le récupère dans la variable 'patientModif'
            Patient patientModif = patientOptional.get();
            // et on met à jour chaque donnée :
            // 'patientDto.getNom()' récupère le nouveau nom du patient à partr du DTO (PatientDTO)
            // et 'patientModif.setNom()' définit le nouveau nom du patient dans l'objet patientModif (Entity)
            patientModif.setNom(patientDto.getNom());
            patientModif.setPrenom(patientDto.getPrenom());
            patientModif.setDateNaissance(patientDto.getDateNaissance());
            patientModif.setNumSecu(patientDto.getNumSecu());
            patientModif.setAdresse(patientDto.getAdresse());
            patientModif.setCp(patientDto.getCp());
            patientModif.setCommune(patientDto.getCommune());
            patientModif.setTelephone(patientDto.getTelephone());
            patientModif.setEmail(patientDto.getEmail());
            patientModif.setDateModif(patientDto.getDateModif());
            // déclation variable 'updatePatient' pour sauvegarder les données du patient modifié (Entity)
            Patient updatePatient = patientRepository.save(patientModif);

            // et on renvoi le patient sauvegardé converti en DTO
            return PatientConvert.getInstance().convertToDto(updatePatient);
        } else {
            throw new PatientIntrouvableException("Patient introuvable avec l'identifiant : " + id);
        }
    }

}
