package pl.migibud.movie;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.migibud.actor.Actor;
import pl.migibud.genre.Genre;

import java.util.Set;

@Entity
@Table(name = "movies")
@Getter
@NoArgsConstructor
@ToString
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "year_of_release")
    private int yearOfRelease;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    @ToString.Exclude
    private Genre genre;
    @ManyToMany(mappedBy = "movies")
    @ToString.Exclude
    private Set<Actor> actors;

    public Movie(String title, int yearOfRelease) {
        this.title = title;
        this.yearOfRelease = yearOfRelease;
    }

    public Movie(String title, int yearOfRelease, Genre genre) {
        this.title = title;
        this.yearOfRelease = yearOfRelease;
        this.genre = genre;
    }
}
