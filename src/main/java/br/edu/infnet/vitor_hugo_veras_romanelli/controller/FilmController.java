package br.edu.infnet.vitor_hugo_veras_romanelli.controller;

import br.edu.infnet.vitor_hugo_veras_romanelli.DTO.FilmRequestDTO;
import br.edu.infnet.vitor_hugo_veras_romanelli.model.domain.*;
import br.edu.infnet.vitor_hugo_veras_romanelli.model.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/films")
public class FilmController {

	@Autowired
	private FilmService filmService;
	@Autowired
	private DirectorService directorService;
	@Autowired
	private ActorService actorService;

	@PostMapping
	@Operation(summary = "Add a new film with details including director and actors")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Film added successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid film data provided")
    })
	public ResponseEntity<?> addFilm(@RequestBody FilmRequestDTO filmRequestDTO) {
		var director = directorService.findById(filmRequestDTO.getDirectorId());
		if (director == null) {
			return ResponseEntity.badRequest().body("Director not found with ID: " + filmRequestDTO.getDirectorId());
		}

		var actors = filmRequestDTO.getActorIds().stream().map(actorService::findById).filter(actor -> actor != null)
				.collect(Collectors.toList());

		if (actors.isEmpty()) {
			return ResponseEntity.badRequest().body("No valid actors found for the provided IDs.");
		}

		Film film = new Film();
		film.setTitle(filmRequestDTO.getTitle());
		film.setReleaseDate(filmRequestDTO.getReleaseDate());
		film.setGenre(filmRequestDTO.getGenre());
		film.setRating(filmRequestDTO.getRating());
		film.setDirector(director);
		film.setActors(actors);

		Film savedFilm = filmService.save(film);
		return ResponseEntity.ok(savedFilm);
	}

	@GetMapping("/title/{title}")
	@Operation(summary = "Find a film by its exact title")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of films retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No films found")
    })
	public ResponseEntity<?> findFilmByTitle(@PathVariable String title) {
		Film film = filmService.findByTitle(title);
		if (film != null) {
			return ResponseEntity.ok(film);
		}
		return ResponseEntity.status(404).body("Film not found with title: " + title);
	}

	@GetMapping("/title/contains/{title}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Film retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Film not found with the provided ID")
    })
	@Operation(summary = "Search for films whose titles contain a specific substring")
	public ResponseEntity<?> findFilmsByTitleContaining(@PathVariable String title) {
		List<Film> films = (List<Film>) filmService.findFilmsByTitleContaining(title);
		if (films.isEmpty()) {
			return ResponseEntity.status(404).body("No films found containing title: " + title);
		}
		return ResponseEntity.ok(films);
	}

	@GetMapping
	@Operation(summary = "Retrieve a list of all films")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of films retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No films found")
    })
	public ResponseEntity<?> getAllFilms() {
		List<Film> films = filmService.findAll();
		if (films.isEmpty()) {
			return ResponseEntity.status(404).body("No films found.");
		}
		return ResponseEntity.ok(films);
	}

	@GetMapping("/count")
	@Operation(summary = "Get the total number of films in the database")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Count of films retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No films found")
    })
	public ResponseEntity<?> getCount() {
		long count = filmService.getCount();
		return ResponseEntity.ok("Total number of films: " + count);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Retrieve a film by its unique ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Film retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No films found")
    })
	public ResponseEntity<?> getFilmById(@PathVariable Long id) {
		Film film = filmService.findById(id);
		if (film != null) {
			return ResponseEntity.ok(film);
		}
		return ResponseEntity.status(404).body("Film not found with ID: " + id);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update the details of an existing film")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Film updated successfully"),
        @ApiResponse(responseCode = "404", description = "Film not found with the provided ID")
    })
	public ResponseEntity<?> updateFilm(@PathVariable Long id, @RequestBody Film updatedFilm) {
		var existingFilm = filmService.findById(id);
		if (existingFilm == null) {
			return ResponseEntity.status(404).body("Film not found with ID: " + id);
		}

		updatedFilm.setId(id);
		Film savedFilm = filmService.update(updatedFilm);
		return ResponseEntity.ok(savedFilm);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a film by its unique ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Film deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Film not found with the provided ID")
    })
	public ResponseEntity<?> deleteFilm(@PathVariable Long id) {
		var existingFilm = filmService.findById(id);
		if (existingFilm == null) {
			return ResponseEntity.status(404).body("Film not found with ID: " + id);
		}

		filmService.delete(id);
		return ResponseEntity.ok("Film with ID " + id + " was successfully deleted.");
	}
}
