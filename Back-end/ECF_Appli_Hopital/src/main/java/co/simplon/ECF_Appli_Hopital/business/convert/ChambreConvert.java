package co.simplon.ECF_Appli_Hopital.business.convert;

import java.util.ArrayList;
import java.util.List;

import co.simplon.ECF_Appli_Hopital.business.dto.ChambreDTO;
import co.simplon.ECF_Appli_Hopital.business.dto.LitDTO;
import co.simplon.ECF_Appli_Hopital.persistence.entity.Chambre;
import co.simplon.ECF_Appli_Hopital.persistence.entity.Lit;

public class ChambreConvert {
    private static ChambreConvert instance;

    public static ChambreConvert getInstance() {
        if (instance == null) {
            instance = new ChambreConvert();
        }
        return instance;
    }

    // Convert Chambre (Entity) en Chambre (DTO)
    public ChambreDTO convertChambreToDTO(final Chambre chEntity) {
        ChambreDTO chDto = new ChambreDTO();
        chDto.setIdChambre(chEntity.getIdChambre());
        chDto.setNumChambre(chEntity.getNumChambre());
        LitDTO litDto = LitConvert.getInstance().convertLitToDTO(chEntity.getLit());
        chDto.setIdLit(litDto);
        return chDto;
    }

    // Convert Chambre (DTO) à Chambre (Entity)
    public Chambre convertChambreToEntity(final ChambreDTO chDto) {
        Chambre chEntity = new Chambre();
        chEntity.setIdChambre(chDto.getIdChambre());
        chEntity.setNumChambre(chDto.getNumChambre());
        Lit lit = LitConvert.getInstance().convertLitToEntity(chDto.getIdLit());
        chEntity.setLit(lit);
        return chEntity;
    }

    // Listes :

    // Convert liste Chambre (Entity) en liste Chambre (DTO)
    public List<ChambreDTO> convertListeChambreToDTO(final List<Chambre> listeChambreEntity) {
        List<ChambreDTO> listChDTO = new ArrayList<>();
        for (final Chambre ch : listeChambreEntity) {
            listChDTO.add(convertChambreToDTO(ch));
        }
        return listChDTO;
    }

    // Convert liste Patient (DTO) en liste Patient (Entity)
    public List<Chambre> convertListeChambreToEntity(final List<ChambreDTO> listeChambreDTO) {
        List<Chambre> listChEntity = new ArrayList<>();
        for (final ChambreDTO chDto : listeChambreDTO) {
            listChEntity.add(convertChambreToEntity(chDto));
        }
        return listChEntity;
    }

}
