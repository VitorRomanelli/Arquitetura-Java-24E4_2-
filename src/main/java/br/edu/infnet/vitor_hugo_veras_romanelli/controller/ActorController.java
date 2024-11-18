package br.edu.infnet.vitor_hugo_veras_romanelli.controller;

import br.edu.infnet.vitor_hugo_veras_romanelli.model.domain.Actor;
import br.edu.infnet.vitor_hugo_veras_romanelli.model.service.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

	@Autowired
    private ActorService actorService;

    @PostMapping
    @Operation(summary = "Add a new actor to the database")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Actor added successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid actor data provided")
    })
    public ResponseEntity<?> addActor(@RequestBody Actor actor) {
        Actor savedActor = actorService.save(actor);
        return ResponseEntity.ok(savedActor);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Find an actor by their exact name")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of actors retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No actors found")
    })
    public ResponseEntity<?> findActorByName(@PathVariable String name) {
        Actor actor = actorService.findByName(name);
        if (actor != null) {
            return ResponseEntity.ok(actor);
        }
        return ResponseEntity.status(404).body("Actor not found with name: " + name);
    }

    @GetMapping("/name/contains/{name}")
    @Operation(summary = "Search for actors whose names contain a specific substring")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of actors retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No actors found")
    })
    public ResponseEntity<?> findActorsByNameContaining(@PathVariable String name) {
        List<Actor> actors = (List<Actor>) actorService.findActorsByNameContaining(name);
        if (actors.isEmpty()) {
            return ResponseEntity.status(404).body("No actors found containing name: " + name);
        }
        return ResponseEntity.ok(actors);
    }

    @GetMapping
    @Operation(summary = "Retrieve a list of all actors")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of actors retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No actors found")
    })
    public ResponseEntity<?> getAllActors() {
        List<Actor> actors = actorService.findAll();
        if (actors.isEmpty()) {
            return ResponseEntity.status(404).body("No actors found.");
        }
        return ResponseEntity.ok(actors);
    }

    @GetMapping("/count")
    @Operation(summary = "Get the total number of actors in the database")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Count of actors retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No actors found")
    })
    public ResponseEntity<?> getCount() {
        long count = actorService.getCount();
        return ResponseEntity.ok("Total number of actors: " + count);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve an actor by their unique ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Actor retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No actor found")
    })
    public ResponseEntity<?> getActorById(@PathVariable Long id) {
        Actor actor = actorService.findById(id);
        if (actor != null) {
            return ResponseEntity.ok(actor);
        }
        return ResponseEntity.status(404).body("Actor not found with ID: " + id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the details of an existing actor")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Actor updated successfully"),
        @ApiResponse(responseCode = "404", description = "Actor not found with the provided ID")
    })
    public ResponseEntity<?> updateActor(@PathVariable Long id, @RequestBody Actor updatedActor) {
        var existingActor = actorService.findById(id);
        if (existingActor == null) {
            return ResponseEntity.status(404).body("Actor not found with ID: " + id);
        }

        updatedActor.setId(id);
        Actor savedActor = actorService.update(updatedActor);
        return ResponseEntity.ok(savedActor);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an actor by their unique ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Actor deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Actor not found with the provided ID")
    })
    public ResponseEntity<?> deleteActor(@PathVariable Long id) {
        var existingActor = actorService.findById(id);
        if (existingActor == null) {
            return ResponseEntity.status(404).body("Actor not found with ID: " + id);
        }

        actorService.delete(id);
        return ResponseEntity.ok("Actor with ID " + id + " was successfully deleted.");
    }
}