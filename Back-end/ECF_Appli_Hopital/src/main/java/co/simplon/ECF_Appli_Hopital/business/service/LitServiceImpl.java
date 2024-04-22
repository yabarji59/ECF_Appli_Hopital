package co.simplon.ECF_Appli_Hopital.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.simplon.ECF_Appli_Hopital.business.convert.LitConvert;
import co.simplon.ECF_Appli_Hopital.business.dto.LitDTO;
import co.simplon.ECF_Appli_Hopital.persistence.entity.Lit;
import co.simplon.ECF_Appli_Hopital.persistence.repository.LitQuery;

@Service
public class LitServiceImpl implements LitService {
    // interaction avec la BDD pour l'entité Lit
    private LitQuery litRepository;

    // constructeur
    public LitServiceImpl(LitQuery litRepository) {
        this.litRepository = litRepository;
    }

    // Afficher la liste des lits existants dans la BDD
    @Override
    public List<LitDTO> afficherListeLits() {
        List<Lit> listeLit = litRepository.findAll();
        return LitConvert.getInstance().convertListeLitToDTO(listeLit);
    }

    // Afficher la liste des lits existants et disponible (='true') dans la BDD
    @Override
    public List<LitDTO> afficherListeLitsDispos() {
        // récupère la listes des lits existants dans la BDD
        List<LitDTO> listeLits = afficherListeLits();
        // liste vide pour stocker les lits dispos 
        List<LitDTO> litsDispos = new ArrayList<>();
        // parcours tous les lits et ajoute ceux qui sont disponibles
        for (LitDTO lit : listeLits) {
            if (lit.getDispoLit() != null && lit.getDispoLit()) {
                litsDispos.add(lit);
            }
        }
        return litsDispos;
    }

    @Override
    public LitDTO afficherLit(Long id) {
        @SuppressWarnings("null")
        Optional<Lit> litOptional = litRepository.findById(id);
        // condition : vérifie si le lit recherché par son id est présent dans la BDD sinon retournera 'null'
        if (litOptional.isPresent()) {
            Lit litTrouve = litOptional.get();
            return LitConvert.getInstance().convertLitToDTO(litTrouve);
        } else {
            return null;
        }
    }
}
