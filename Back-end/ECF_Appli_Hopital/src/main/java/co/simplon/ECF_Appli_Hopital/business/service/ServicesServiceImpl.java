// package co.simplon.ECF_Appli_Hopital.business.service;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import org.springframework.stereotype.Service;

// import co.simplon.ECF_Appli_Hopital.business.convert.ServicesConvert;
// import co.simplon.ECF_Appli_Hopital.business.dto.ChambreDTO;
// import co.simplon.ECF_Appli_Hopital.business.dto.ServicesDTO;
// import co.simplon.ECF_Appli_Hopital.persistence.entity.Services;
// import co.simplon.ECF_Appli_Hopital.persistence.repository.ServicesQuery;

// @Service
// public class ServicesServiceImpl implements ServicesService {

//     private ServicesQuery servicesRepository;
//     private ChambreService chambreService;

//     public ServicesServiceImpl(ServicesQuery servicesRepository, ChambreService chambreService) {
//         this.servicesRepository = servicesRepository;
//         this.chambreService = chambreService;
//     }

//     @Override
//     public ServicesDTO afficherService(Long id) {
//         @SuppressWarnings("null")
//         Optional<Services> servOptional = servicesRepository.findById(id);
//         // condition : vérifie si le service recherché existe dans la BDD
//         if (servOptional.isPresent()) {
//             Services serviceTrouve = servOptional.get();
//             return ServicesConvert.getInstance().convertServicesToDTO(serviceTrouve);
//         } else {
//             return null;
//         }
//     }

//     @Override
//     public List<ServicesDTO> afficherListeServices() {
//         List<Services> listeServices = servicesRepository.findAll();
//         return ServicesConvert.getInstance().convertListeServicesToDTO(listeServices);
//     }

//     @Override
//     public List<ServicesDTO> afficherListeChambresServices() {
//         // récupére la liste des chambres disponibles
//         List<ChambreDTO> chambreDispos = chambreService.afficherListeChambresDispos();
//         List<ServicesDTO> servChambre = new ArrayList<>();

//         // trouve le service associé à chaque chambre disponible
//         for (ChambreDTO chambre : chambreDispos) {
//             // récupére l'id du service associé à la chambre
//             Long idService = chambre.getIdService();
//             // prend l'id du service pour obtenir les détails du service
//             ServicesDTO serviceDTO = afficherService(idService);
//             // Ajout du service associé à la chambre à la liste
//             servChambre.add(serviceDTO);
//         }

//         return servChambre;
//     }

// }
