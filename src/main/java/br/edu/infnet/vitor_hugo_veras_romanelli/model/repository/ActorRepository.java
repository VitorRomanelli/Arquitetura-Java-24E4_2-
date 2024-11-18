package br.edu.infnet.vitor_hugo_veras_romanelli.model.repository;

import br.edu.infnet.vitor_hugo_veras_romanelli.model.domain.*;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Long> {
    Actor findByName(String name);
    Iterable<Actor> findByNameContainingIgnoreCase(String name);
}
