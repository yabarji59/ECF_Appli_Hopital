package co.simplon.ECF_Appli_Hopital.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.ECF_Appli_Hopital.persistence.entity.Lit;

public interface LitQuery extends JpaRepository<Lit, Long> {

}
