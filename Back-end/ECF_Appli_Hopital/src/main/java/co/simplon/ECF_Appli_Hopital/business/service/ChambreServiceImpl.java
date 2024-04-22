package co.simplon.ECF_Appli_Hopital.business.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.simplon.ECF_Appli_Hopital.business.convert.ChambreConvert;
import co.simplon.ECF_Appli_Hopital.business.dto.ChambreDTO;
import co.simplon.ECF_Appli_Hopital.persistence.entity.Chambre;
import co.simplon.ECF_Appli_Hopital.persistence.repository.ChambreQuery;
import co.simplon.ECF_Appli_Hopital.persistence.repository.LitQuery;

@Service
public class ChambreServiceImpl implements ChambreService {

    private ChambreQuery chambreRepository;
    private LitQuery litRepository;
    private LitService litServ;

    // constructeur
    public ChambreServiceImpl(ChambreQuery chambreRepository, LitQuery litRepository, LitService litServ) {
        this.chambreRepository = chambreRepository;
        this.litRepository = litRepository;
        this.litServ = litServ;

    }

    @Override
    public List<ChambreDTO> afficherListeChambresDispos() {
        List<Chambre> litsDispos = chambreRepository.findAll();
        return ChambreConvert.getInstance().convertListeChambreToDTO(litsDispos);
    }

    @Override
    public ChambreDTO afficherChambre(Long id) {
        @SuppressWarnings("null")
        Optional<Chambre> chambreOptional = chambreRepository.findById(id);
        // condition : vérifie si la chambre rechercheé est présente dans la BDD
        if (chambreOptional.isPresent()) {
            Chambre chambreTrouve = chambreOptional.get();
            return ChambreConvert.getInstance().convertChambreToDTO(chambreTrouve);
        } else {
            return null;
        }
    }

}
