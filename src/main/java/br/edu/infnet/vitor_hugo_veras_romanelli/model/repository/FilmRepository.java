package br.edu.infnet.vitor_hugo_veras_romanelli.model.repository;

import br.edu.infnet.vitor_hugo_veras_romanelli.model.domain.*;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film, Long> {
    Film findByTitle(String title);
    Iterable<Film> findByTitleContainingIgnoreCase(String title);
}
