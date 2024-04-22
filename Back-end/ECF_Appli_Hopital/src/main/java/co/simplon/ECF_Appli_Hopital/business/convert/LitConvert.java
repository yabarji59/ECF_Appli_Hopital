package co.simplon.ECF_Appli_Hopital.business.convert;

import java.util.ArrayList;
import java.util.List;

import co.simplon.ECF_Appli_Hopital.business.dto.LitDTO;
import co.simplon.ECF_Appli_Hopital.persistence.entity.Lit;

public class LitConvert {
    private static LitConvert instance;

    public static LitConvert getInstance() {
        if (instance == null) {
            instance = new LitConvert();
        }
        return instance;
    }

    // Convert Patient (Entity) en Patient(DTO)
    public LitDTO convertLitToDTO(final Lit litEntity) {
        LitDTO litDto = new LitDTO();
        litDto.setIdLit(litEntity.getIdLit());
        litDto.setDispoLit(litEntity.getDispoLit());
        return litDto;
    }

    // Convert Patient (DTO) à Patient(Entity)
    public Lit convertLitToEntity(final LitDTO litDto) {
        Lit litEntity = new Lit();
        litEntity.setIdLit(litDto.getIdLit());
        litEntity.setDispoLit(litDto.getDispoLit());
        return litEntity;
    }

    // Listes :

    // Convert liste Patient (Entity) en liste Patient (DTO)
    public List<LitDTO> convertListeLitToDTO(final List<Lit> listeLitEntity) {
        List<LitDTO> listeLitDTO = new ArrayList<>();
        for (final Lit l : listeLitEntity) {
            listeLitDTO.add(convertLitToDTO(l));
        }
        return listeLitDTO;
    }

    // Convert liste Patient (DTO) en liste Patient (Entity)
    public List<Lit> convertListeLitToEntity(final List<LitDTO> listeLitDTO) {
        List<Lit> listeLitEntity = new ArrayList<>();
        for (final LitDTO lDto : listeLitDTO) {
            listeLitEntity.add(convertLitToEntity(lDto));
        }
        return listeLitEntity;
    }
}
