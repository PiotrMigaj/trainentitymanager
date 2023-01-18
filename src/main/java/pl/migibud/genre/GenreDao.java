package pl.migibud.genre;

import pl.migibud.Dao;

import java.util.Optional;

public interface GenreDao extends Dao<Genre,Long> {
    Optional<Genre> findByType(Genre.Type type);
}
