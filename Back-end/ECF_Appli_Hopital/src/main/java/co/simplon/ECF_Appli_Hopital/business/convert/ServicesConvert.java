// package co.simplon.ECF_Appli_Hopital.business.convert;

// import java.util.ArrayList;
// import java.util.List;

// import co.simplon.ECF_Appli_Hopital.business.dto.ServicesDTO;
// import co.simplon.ECF_Appli_Hopital.persistence.entity.Services;

// public class ServicesConvert {
//     private static ServicesConvert instance;

//     public static ServicesConvert getInstance() {
//         if (instance == null) {
//             instance = new ServicesConvert();
//         }
//         return instance;
//     }

//     // Convert Services (Entity) en Services (DTO)
//     public ServicesDTO convertServicesToDTO(final Services servEntity) {
//         ServicesDTO servDTO = new ServicesDTO();
//         servDTO.setIdService(servEntity.getIdService());
//         servDTO.setNomService(servEntity.getNomService());
//         servDTO.setIdChambre(servEntity.getChambre().getIdChambre());
//         return servDTO;
//     }

//     // Convert Services (DTO) à Services (Entity)
//     public Services convertServicesToEntity(final ServicesDTO servDto) {
//         Services servEntity = new Services();
//         servEntity.setIdService(servDto.getIdService());
//         servEntity.setNomService(servDto.getNomService());
//         ;
//         return servEntity;
//     }

//     // Listes :

//     // Convert liste Services (Entity) en liste Services (DTO)
//     public List<ServicesDTO> convertListeServicesToDTO(final List<Services> listeServicesEntity) {
//         List<ServicesDTO> listServDTO = new ArrayList<>();
//         for (final Services serv : listeServicesEntity) {
//             listServDTO.add(convertServicesToDTO(serv));
//         }
//         return listServDTO;
//     }

//     // Convert liste Patient (DTO) en liste Patient (Entity)
//     public List<Services> convertListeServicesToEntity(final List<ServicesDTO> listeServicesDTO) {
//         List<Services> listServEntity = new ArrayList<>();
//         for (final ServicesDTO servDto : listeServicesDTO) {
//             listServEntity.add(convertServicesToEntity(servDto));
//         }
//         return listServEntity;
//     }

// }
