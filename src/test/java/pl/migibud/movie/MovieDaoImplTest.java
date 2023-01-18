package pl.migibud.movie;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.migibud.DbConnection;
import pl.migibud.genre.Genre;
import pl.migibud.genre.GenreDao;
import pl.migibud.genre.GenreDaoImpl;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MovieDaoImplTest {

    private static SessionFactory sessionFactory;
    private MovieDao movieDao;


    @BeforeAll
    static void setUpDbConnection() {
        sessionFactory = DbConnection.getSessionFactory();
    }

    @AfterAll
    static void closeDbConnection() {
        DbConnection.close();
    }

    @BeforeEach
    void setUp() {
        movieDao = new MovieDaoImpl(sessionFactory);
    }

    @Test
    void givenMovie_whenSaveInDb_thenShouldReturnSavedEntity() {
        //given
        Movie movie = new Movie("Harry Potter", 2002);
        //when
        Movie savedMovie = movieDao.save(movie);
        System.out.println(savedMovie);
        //then
        assertAll(
                () -> assertThat(savedMovie.getId()).isNotNull(),
                () -> assertThat(savedMovie.getId()).isEqualTo(1L)
        );
    }

    @Test
    void givenMoviesInTheDb_whenFindMoviesWithSpecifiedGenreType_thenShouldReturnListOfMoviesWithSpecifiedGenreType() {
        //given
        GenreDao genreDao = new GenreDaoImpl(sessionFactory);
        Genre genreFantasy = new Genre(Genre.Type.FANTASY);
        Genre genreHorror = new Genre(Genre.Type.HORROR);
        genreDao.save(genreFantasy);
        genreDao.save(genreHorror);

        Movie movie1 = new Movie("Harry Potter", 2002, genreFantasy);
        Movie movie2 = new Movie("Harry Potter 2", 2005, genreFantasy);
        Movie movie3 = new Movie("Omen", 1996, genreHorror);
        movieDao.save(movie1);
        movieDao.save(movie2);
        movieDao.save(movie3);

        //when
        List<Movie> movies = movieDao.findAllByGenreType(Genre.Type.FANTASY);
        List<String> titles = movies.stream()
                .map(Movie::getTitle)
                .collect(Collectors.toList());
        System.out.println(movies);
        //then
        assertAll(
                () -> assertThat(movies).isNotEmpty(),
                () -> assertThat(movies).hasSize(2),
                () -> assertThat(titles).containsExactly(movie1.getTitle(),movie2.getTitle())
        );
    }

}