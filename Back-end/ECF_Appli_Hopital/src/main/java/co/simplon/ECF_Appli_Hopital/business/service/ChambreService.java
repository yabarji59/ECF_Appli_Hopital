package co.simplon.ECF_Appli_Hopital.business.service;

import java.util.List;

import co.simplon.ECF_Appli_Hopital.business.dto.ChambreDTO;

public interface ChambreService {

    ChambreDTO afficherChambre(Long id);

    List<ChambreDTO> afficherListeChambresDispos();
}
