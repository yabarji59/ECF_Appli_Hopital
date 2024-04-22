package co.simplon.ECF_Appli_Hopital.business.service;

import java.util.List;

import co.simplon.ECF_Appli_Hopital.business.dto.LitDTO;

public interface LitService {

    List<LitDTO> afficherListeLits();

    List<LitDTO> afficherListeLitsDispos();

    LitDTO afficherLit(Long id);

}
