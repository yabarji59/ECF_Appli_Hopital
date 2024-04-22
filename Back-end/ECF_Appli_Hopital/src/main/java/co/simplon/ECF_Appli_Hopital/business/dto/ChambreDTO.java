package co.simplon.ECF_Appli_Hopital.business.dto;

public class ChambreDTO {
    private long idChambre;
    private String numChambre;
    private LitDTO idLit; // clé étrangère de la Table Lit

    private Long idService;

    public long getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(long idChambre) {
        this.idChambre = idChambre;
    }

    public String getNumChambre() {
        return numChambre;
    }

    public void setNumChambre(String numChambre) {
        this.numChambre = numChambre;
    }

    public LitDTO getIdLit() {
        return idLit;
    }

    public void setIdLit(LitDTO idLit) {
        this.idLit = idLit;
    }

    public Long getIdService() {
        return idService;
    }

    public void setIdService(Long idService) {
        this.idService = idService;
    }

}
