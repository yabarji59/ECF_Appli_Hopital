// package co.simplon.ECF_Appli_Hopital.persistence.entity;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "services")

// public class Services {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)

//     @Column(name = "id_service")
//     private long idService;

//     @Column(name = "nom_service")
//     private String nomService;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "id_chambre")
//     private Chambre chambre;

//     // getters et setters

//     public long getIdService() {
//         return idService;
//     }

//     public void setIdService(long idService) {
//         this.idService = idService;
//     }

//     public String getNomService() {
//         return nomService;
//     }

//     public void setNomService(String nomService) {
//         this.nomService = nomService;
//     }

//     public Chambre getChambre() {
//         return chambre;
//     }

//     public void setChambre(Chambre chambre) {
//         this.chambre = chambre;
//     }

// }
