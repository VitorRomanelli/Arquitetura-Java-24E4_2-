package br.edu.infnet.vitor_hugo_veras_romanelli.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.vitor_hugo_veras_romanelli.model.domain.Director;
import br.edu.infnet.vitor_hugo_veras_romanelli.model.repository.DirectorRepository;

@Service
public class DirectorService {

	@Autowired
    private DirectorRepository directorRepository;

    public Director save(Director director) {
        return directorRepository.save(director);
    }

    public Director findByName(String name) {
        return directorRepository.findByName(name);
    }

    public Iterable<Director> findDirectorsByNameContaining(String name) {
        return directorRepository.findByNameContainingIgnoreCase(name);
    }
    
    public List<Director> findAll() {
        return (List<Director>) directorRepository.findAll();
    }

    public Director findById(Long id) {
        return directorRepository.findById(id).orElse(null);
    }

    public long getCount() {
    	return directorRepository.count();
    }

    public Director update(Director director) {
        if (director.getId() != null && directorRepository.existsById(director.getId())) {
            return directorRepository.save(director);
        } else {
            throw new RuntimeException("Director not found for update.");
        }
    }

    public void delete(Long id) {
        if (directorRepository.existsById(id)) {
            directorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Director not found for deletion.");
        }
    }
}
