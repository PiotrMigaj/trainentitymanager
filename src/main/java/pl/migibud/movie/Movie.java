package pl.migibud.movie;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import pl.migibud.actor.Actor;
import pl.migibud.genre.Genre;

import java.util.Set;

@Entity
@Table(name = "movies")
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "year_of_release")
    private int yearOfRelease;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;
    @ManyToMany(mappedBy = "movies")
    private Set<Actor> actors;

    public Movie(String title, int yearOfRelease, Genre genre) {
        this.title = title;
        this.yearOfRelease = yearOfRelease;
        this.genre = genre;
    }
}
