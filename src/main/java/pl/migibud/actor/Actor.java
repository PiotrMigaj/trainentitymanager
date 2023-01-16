package pl.migibud.actor;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import pl.migibud.movie.Movie;

import java.util.Set;

@Entity
@Table(name = "actors")
@NoArgsConstructor
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
            name = "actors_to_movies",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> movies;
}
