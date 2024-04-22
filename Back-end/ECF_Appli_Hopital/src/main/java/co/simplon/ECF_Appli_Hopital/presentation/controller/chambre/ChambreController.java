package co.simplon.ECF_Appli_Hopital.presentation.controller.chambre;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.ECF_Appli_Hopital.business.dto.ChambreDTO;
import co.simplon.ECF_Appli_Hopital.business.service.ChambreService;

@RestController
public class ChambreController {

    private final ChambreService chambreServ;

    public ChambreController(ChambreService chambreServ) {
        this.chambreServ = chambreServ;
    }

    @CrossOrigin
    @GetMapping("/chambre")
    public List<ChambreDTO> listerChambreDispos() {
        return chambreServ.afficherListeChambresDispos();
    }

    @CrossOrigin
    @GetMapping("/chambre/{id}")
    public ChambreDTO afficherChambreParID(@PathVariable("id") Long id) {
        // renvoie les détails d'un lit existant par son identifiant
        return chambreServ.afficherChambre(id);
    }
}
