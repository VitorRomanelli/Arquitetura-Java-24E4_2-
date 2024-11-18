package br.edu.infnet.vitor_hugo_veras_romanelli;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.vitor_hugo_veras_romanelli.model.domain.*;
import br.edu.infnet.vitor_hugo_veras_romanelli.model.service.ActorService;
import br.edu.infnet.vitor_hugo_veras_romanelli.model.service.DirectorService;
import br.edu.infnet.vitor_hugo_veras_romanelli.model.service.FilmService;
import br.edu.infnet.vitor_hugo_veras_romanelli.model.service.SpecialEditionFilmService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Loader implements ApplicationRunner {

    @Autowired
    private FilmService filmService;

    @Autowired
    private DirectorService directorService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private SpecialEditionFilmService specialEditionFilmService;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
    	 ClassLoader classLoader = getClass().getClassLoader();
         InputStream inputStream = classLoader.getResourceAsStream("films_library.csv");

         if (inputStream == null) {
             throw new IllegalArgumentException("File not found: films_library.csv");
         }

         BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        List<Actor> actors = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(";");
            switch (fields[0].toUpperCase()) {
                case "D":
                    Director newDirector = new Director();
                    newDirector.setId(Long.valueOf(fields[1]));
                    newDirector.setName(fields[2]);
                    newDirector.setAge(Integer.valueOf(fields[3]));
                    directorService.save(newDirector);
                    System.out.println("DIRECTOR ADDED: " + newDirector.getName());
                    break;
                    
                case "F":
                    Film film = new Film();
                    film.setId(Long.valueOf(fields[1]));
                    film.setTitle(fields[2]);
                    film.setReleaseDate(java.time.LocalDate.parse(fields[3]));
                    film.setGenre(fields[4]);
                    film.setRating(Double.valueOf(fields[5]));
                    Director director = directorService.findById(Long.valueOf(fields[6]));
                    film.setDirector(director);
                    filmService.save(film);
                    System.out.println("FILM ADDED: " + film.getTitle());
                    break;

                case "A":
                    Actor actor = new Actor();
                    actor.setId(Long.valueOf(fields[1]));
                    actor.setName(fields[2]);
                    actor.setAge(Integer.valueOf(fields[3]));
                    actorService.save(actor);
                    actors.add(actor);
                    System.out.println("ACTOR ADDED: " + actor.getName());
                    break;
                    
                case "SE":
                    String filmTitle = fields[1];
                    LocalDate releaseDate = LocalDate.parse(fields[2]);
                    String genre = fields[3];
                    double rating = Double.parseDouble(fields[4]);
                    Long directorId = Long.parseLong(fields[5]);
                    String bonusFeatures = fields[6];
                    String specialFeature = fields[7];
                    String editionType = fields[8];
                    String packagingType = fields[9];

                    Director sDirector = directorService.findById(directorId);
                    SpecialEditionFilm specialEditionFilm = new SpecialEditionFilm(filmTitle, releaseDate, genre, rating, sDirector, bonusFeatures, specialFeature, editionType, packagingType);
                    specialEditionFilmService.save(specialEditionFilm);
                    break;


                default:
                    System.out.println("UNKNOWN ENTRY: " + line);
            }
        }

        reader.close();

        for (Film film : filmService.findAll()) {
            film.setActors(actors.subList(0, 4));
            filmService.save(film);
        }

        System.out.println("LOADING COMPLETE.");
    }
}
