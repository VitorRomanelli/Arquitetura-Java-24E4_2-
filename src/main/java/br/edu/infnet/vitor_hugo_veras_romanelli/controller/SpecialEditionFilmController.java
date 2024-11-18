package br.edu.infnet.vitor_hugo_veras_romanelli.controller;

import br.edu.infnet.vitor_hugo_veras_romanelli.model.domain.SpecialEditionFilm;
import br.edu.infnet.vitor_hugo_veras_romanelli.model.service.SpecialEditionFilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialEditionFilms")
public class SpecialEditionFilmController {

	@Autowired
    private SpecialEditionFilmService specialEditionFilmService;

    @PostMapping
    @Operation(summary = "Add a new special edition film")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Special edition film added successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid film data provided"),
    })
    public ResponseEntity<SpecialEditionFilm> addFilm(@RequestBody SpecialEditionFilm film) {
        specialEditionFilmService.save(film);
        return ResponseEntity.ok(film);
    }

    @GetMapping
    @Operation(summary = "Retrieve a list of all special edition films")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of special edition films retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No special edition films found")
    })
    public ResponseEntity<List<SpecialEditionFilm>> getAllFilms() {
        List<SpecialEditionFilm> films = specialEditionFilmService.findAll();
        if (films.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(films);
    }

    @GetMapping("count")
    @Operation(summary = "Get the total number of special edition films")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Special edition film count retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No Films found")
    })
    public ResponseEntity<Long> getCount() {
        long count = specialEditionFilmService.getCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a special edition film by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Special edition film retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Special edition film not found with the provided ID")
    })
    public ResponseEntity<SpecialEditionFilm> getById(@PathVariable Long id) {
        SpecialEditionFilm film = specialEditionFilmService.findById(id);
        if (film != null) {
            return ResponseEntity.ok(film);
        }
        return ResponseEntity.status(404).body(null);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a special edition film")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Special edition film updated successfully"),
        @ApiResponse(responseCode = "404", description = "Special edition film not found with the provided ID")
    })
    public ResponseEntity<SpecialEditionFilm> updateFilm(@PathVariable Long id, @RequestBody SpecialEditionFilm film) {
        film.setId(id);
        try {
            specialEditionFilmService.update(film);
            return ResponseEntity.ok(film);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a special edition film by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Special edition film deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Special edition film not found with the provided ID")
    })
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        try {
            specialEditionFilmService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }
}
