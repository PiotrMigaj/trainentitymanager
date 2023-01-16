package pl.migibud.genre;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.migibud.movie.Movie;

import java.util.Set;

@Entity
@Table(name = "genres")
@Getter
@NoArgsConstructor
@ToString
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String name;
    @ToString.Exclude
    @OneToMany(mappedBy = "genre")
    private Set<Movie> movies;

    public Genre(String name) {
        this.name = name;
    }
}
