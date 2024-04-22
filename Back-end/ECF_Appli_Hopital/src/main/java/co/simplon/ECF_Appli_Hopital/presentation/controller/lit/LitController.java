package co.simplon.ECF_Appli_Hopital.presentation.controller.lit;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.ECF_Appli_Hopital.business.dto.LitDTO;
import co.simplon.ECF_Appli_Hopital.business.service.LitService;

@RestController
public class LitController {

    private final LitService litServ;

    // contructeur
    public LitController(LitService litServ) {
        this.litServ = litServ;
    }

    @CrossOrigin // permet d'interagir avec le Front (chemin)
    @GetMapping("/lit")
    public List<LitDTO> listerLitsDispos() {
        // renvoi la liste de tous les lits disponibles
        return litServ.afficherListeLits();
    }

    @CrossOrigin
    @GetMapping("/lit/{id}")
    public LitDTO afficherLitParID(@PathVariable Long id) {
        // renvoie les détails d'un lit existant par son identifiant
        return litServ.afficherLit(id);
    }

}
