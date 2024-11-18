package br.edu.infnet.vitor_hugo_veras_romanelli.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.infnet.vitor_hugo_veras_romanelli.model.domain.SpecialEditionFilm;

public interface SpecialEditionFilmRepository extends JpaRepository<SpecialEditionFilm, Long> {
}