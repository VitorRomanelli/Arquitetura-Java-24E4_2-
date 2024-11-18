package br.edu.infnet.vitor_hugo_veras_romanelli.controller;

import br.edu.infnet.vitor_hugo_veras_romanelli.model.domain.Director;
import br.edu.infnet.vitor_hugo_veras_romanelli.model.service.DirectorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directors")
public class DirectorController {

	@Autowired
    private DirectorService directorService;

    @PostMapping
    @Operation(summary = "Add a new director to the database")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Director added successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid director data provided")
    })
    public ResponseEntity<?> addDirector(@RequestBody Director director) {
        Director savedDirector = directorService.save(director);
        return ResponseEntity.ok(savedDirector);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Find a director by their exact name")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Director retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No director found")
    })
    public ResponseEntity<?> findDirectorByName(@PathVariable String name) {
        Director director = directorService.findByName(name);
        if (director != null) {
            return ResponseEntity.ok(director);
        }
        return ResponseEntity.status(404).body("Director not found with name: " + name);
    }

    @GetMapping("/name/contains/{name}")
    @Operation(summary = "Search for directors whose names contain a specific substring")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of directors retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No directors found")
    })
    public ResponseEntity<?> findDirectorsByNameContaining(@PathVariable String name) {
        List<Director> directors = (List<Director>) directorService.findDirectorsByNameContaining(name);
        if (directors.isEmpty()) {
            return ResponseEntity.status(404).body("No directors found containing name: " + name);
        }
        return ResponseEntity.ok(directors);
    }

    @GetMapping
    @Operation(summary = "Retrieve a list of all directors")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of directors retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No directors found")
    })
    public ResponseEntity<?> getAllDirectors() {
        List<Director> directors = directorService.findAll();
        if (directors.isEmpty()) {
            return ResponseEntity.status(404).body("No directors found.");
        }
        return ResponseEntity.ok(directors);
    }

    @GetMapping("/count")
    @Operation(summary = "Get the total number of directors in the database")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Count of directors retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No directors found")
    })
    public ResponseEntity<?> getCount() {
        long count = directorService.getCount();
        return ResponseEntity.ok("Total number of directors: " + count);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a director by their unique ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Director retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No directors found")
    })
    public ResponseEntity<?> getDirectorById(@PathVariable Long id) {
        Director director = directorService.findById(id);
        if (director != null) {
            return ResponseEntity.ok(director);
        }
        return ResponseEntity.status(404).body("Director not found with ID: " + id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the details of an existing director")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Director updated successfully"),
        @ApiResponse(responseCode = "404", description = "Director not found with the provided ID")
    })
    public ResponseEntity<?> updateDirector(@PathVariable Long id, @RequestBody Director updatedDirector) {
        var existingDirector = directorService.findById(id);
        if (existingDirector == null) {
            return ResponseEntity.status(404).body("Director not found with ID: " + id);
        }

        updatedDirector.setId(id);
        Director savedDirector = directorService.update(updatedDirector);
        return ResponseEntity.ok(savedDirector);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a director by their unique ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Director deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Director not found with the provided ID")
    })
    public ResponseEntity<?> deleteDirector(@PathVariable Long id) {
        var existingDirector = directorService.findById(id);
        if (existingDirector == null) {
            return ResponseEntity.status(404).body("Director not found with ID: " + id);
        }

        directorService.delete(id);
		return ResponseEntity.ok("Director with ID " + id + " was successfully deleted.");
	}
}
