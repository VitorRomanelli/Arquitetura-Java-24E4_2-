package br.edu.infnet.vitor_hugo_veras_romanelli.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.vitor_hugo_veras_romanelli.model.domain.Actor;
import br.edu.infnet.vitor_hugo_veras_romanelli.model.repository.ActorRepository;

@Service
public class ActorService {

    @Autowired
	private ActorRepository actorRepository;

    public Actor save(Actor actor) {
        return actorRepository.save(actor);
    }

    public Actor findByName(String name) {
        return actorRepository.findByName(name);
    }

    public Iterable<Actor> findActorsByNameContaining(String name) {
        return actorRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Actor> findAll() {
        return (List<Actor>) actorRepository.findAll();
    }

    public Actor findById(Long id) {
        return actorRepository.findById(id).orElse(null);
    }

    public long getCount() {
    	return actorRepository.count();
    }

    public Actor update(Actor actor) {
        if (actor.getId() != null && actorRepository.existsById(actor.getId())) {
            return actorRepository.save(actor);
        } else {
            throw new RuntimeException("Actor not found for update.");
        }
    }

    public void delete(Long id) {
        if (actorRepository.existsById(id)) {
            actorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Actor not found for deletion.");
        }
    }
}
