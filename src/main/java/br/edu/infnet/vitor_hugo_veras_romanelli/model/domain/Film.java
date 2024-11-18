package br.edu.infnet.vitor_hugo_veras_romanelli.model.domain;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;

@Entity
@Table(name = "TFilm")
@Inheritance(strategy = InheritanceType.JOINED)
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotBlank(message = "Film title is required")
	@Size(min = 3, max = 50, message = "Film title must be between 3 and 50 characters.")
    private String title;

    private LocalDate releaseDate;

	@NotBlank(message = "Film genre is required")
    private String genre;

    private Double rating;
    
    @ManyToOne
    @JoinColumn(name = "director_id", nullable = false)
    @JsonManagedReference
    private Director director;

    @ManyToMany
    @JoinTable(
        name = "film_actor",
        joinColumns = @JoinColumn(name = "film_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @JsonManagedReference
    private List<Actor> actors;

    public Film() {}

    public Film(String title, LocalDate releaseDate, String genre, Double rating, Director director) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.rating = rating;
        this.director = director;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}