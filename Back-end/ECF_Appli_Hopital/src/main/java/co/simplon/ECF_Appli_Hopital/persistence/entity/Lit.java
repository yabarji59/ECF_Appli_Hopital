package co.simplon.ECF_Appli_Hopital.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lit")

public class Lit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // = SERIAL en SQL

    @Column(name = "id_lit")
    private long idLit;

    @Column(name = "disponible")
    private Boolean dispoLit;

    public long getIdLit() {
        return idLit;
    }

    public void setIdLit(long idLit) {
        this.idLit = idLit;
    }

    public Boolean getDispoLit() {
        return dispoLit;
    }

    public void setDispoLit(Boolean dispoLit) {
        this.dispoLit = dispoLit;
    }
}
