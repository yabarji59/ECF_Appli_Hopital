// package co.simplon.ECF_Appli_Hopital.presentation.controller.servicesH;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RestController;

// import co.simplon.ECF_Appli_Hopital.business.dto.ChambreDTO;
// import co.simplon.ECF_Appli_Hopital.business.dto.ServicesDTO;
// import co.simplon.ECF_Appli_Hopital.business.service.ChambreService;
// import co.simplon.ECF_Appli_Hopital.business.service.ServicesService;
// @RestController
// public class ServicesController {
//     private final ServicesService servicesServ;
//     private ChambreService chambreService;

//     // constructeur
//     public ServicesController(ServicesService servicesService, ChambreService chambreService) {
//         this.servicesServ = servicesService;
//         this.chambreService = chambreService;
//     }

//     @CrossOrigin
//     @GetMapping("/services/{id}")
//     public ServicesDTO afficherServiceParID(@PathVariable Long id) {
//         // renvoie les détails d'un service existant par son identifiant
//         return servicesServ.afficherService(id);
//     }

//     @GetMapping("/services")
//     public List<ServicesDTO> afficherListeServices() {
//         // récupère la liste de tous les services existants
//         List<ServicesDTO> listeServices = servicesServ.afficherListeServices();
//         // retourne la liste des services au format DTO
//         return listeServices;
//     }

//     @GetMapping("/chambres/services")
//     public List<ServicesDTO> afficherListeChambresServices() {
//         List<ChambreDTO> chambreDispos = chambreService.afficherListeChambresDispos();
//         List<ServicesDTO> servChambre = new ArrayList<>();

//         for (ChambreDTO chambre : chambreDispos) {
//             Long idService = chambre.getIdService();
//             // appel directement la méthode correspondante dans votre service ServicesService
//             ServicesDTO serviceDTO = servicesServ.afficherService(idService);
//             servChambre.add(serviceDTO);
//         }

//         return servChambre;
//     }

// }
