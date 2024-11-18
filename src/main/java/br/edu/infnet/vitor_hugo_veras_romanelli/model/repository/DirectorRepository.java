package br.edu.infnet.vitor_hugo_veras_romanelli.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.vitor_hugo_veras_romanelli.model.domain.Director;

@Repository
public interface DirectorRepository extends CrudRepository<Director, Long> {
    Director findByName(String name);
    Iterable<Director> findByNameContainingIgnoreCase(String name);
}
