package br.edu.infnet.vitor_hugo_veras_romanelli.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.vitor_hugo_veras_romanelli.model.domain.SpecialEditionFilm;
import br.edu.infnet.vitor_hugo_veras_romanelli.model.repository.SpecialEditionFilmRepository;

@Service
public class SpecialEditionFilmService {

    @Autowired
    private SpecialEditionFilmRepository specialEditionFilmRepository;

    public void save(SpecialEditionFilm specialEditionFilm) {
        specialEditionFilmRepository.save(specialEditionFilm);
    }
    
    public List<SpecialEditionFilm> findAll() {
        return (List<SpecialEditionFilm>) specialEditionFilmRepository.findAll();
    }

    public SpecialEditionFilm findById(Long id) {
        return specialEditionFilmRepository.findById(id).orElse(null);
    }

    public long getCount() {
        return specialEditionFilmRepository.count();
    }
    
    public void update(SpecialEditionFilm film) {
        if (film.getId() != null && specialEditionFilmRepository.existsById(film.getId())) {
        	specialEditionFilmRepository.save(film);
        } else {
            throw new RuntimeException("Film not found for update.");
        }
    }

    public void delete(Long id) {
        if (specialEditionFilmRepository.existsById(id)) {
        	specialEditionFilmRepository.deleteById(id);
        } else {
            throw new RuntimeException("Film not found for deletion.");
        }
    }
}