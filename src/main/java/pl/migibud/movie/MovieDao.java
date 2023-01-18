package pl.migibud.movie;

import pl.migibud.Dao;
import pl.migibud.genre.Genre;

import java.util.List;
import java.util.Optional;

public interface MovieDao extends Dao<Movie,Long> {
    Optional<Movie> findByTitle(String title);
    List<Movie> findAllByGenreType(Genre.Type type);
}
