package br.edu.infnet.vitor_hugo_veras_romanelli.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.vitor_hugo_veras_romanelli.model.domain.*;
import br.edu.infnet.vitor_hugo_veras_romanelli.model.repository.*;

@Service
public class FilmService {

	@Autowired
    private FilmRepository filmRepository;
    
	public Film save(Film film) {
        return filmRepository.save(film);
    }
    
    public Film findByTitle(String title) {
        return filmRepository.findByTitle(title);
    }
    
    public Iterable<Film> findFilmsByTitleContaining(String title) {
        return filmRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Film> findAll() {
        return (List<Film>) filmRepository.findAll();
    }

    public Film findById(Long id) {
        return filmRepository.findById(id).orElse(null);
    }

    public long getCount() {
    	return filmRepository.count();
    }

    public Film update(Film film) {
        if (film.getId() != null && filmRepository.existsById(film.getId())) {
            return filmRepository.save(film);
        } else {
            throw new RuntimeException("Film not found for update.");
        }
    }

    public void delete(Long id) {
        if (filmRepository.existsById(id)) {
            filmRepository.deleteById(id);
        } else {
            throw new RuntimeException("Film not found for deletion.");
        }
    }
}