package pl.migibud;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.SessionFactory;
import pl.migibud.genre.Genre;
import pl.migibud.genre.GenreDao;
import pl.migibud.genre.GenreDaoImpl;
import pl.migibud.movie.Movie;
import pl.migibud.movie.MovieDao;
import pl.migibud.movie.MovieDaoImpl;

import java.util.List;

public class AppMovie {
    public static void main(String[] args) {
        SessionFactory sessionFactory = DbConnection.getSessionFactory();

//        GenreDao genreDao = new GenreDaoImpl(sessionFactory);
//
//        Genre genreComedy = genreDao.findByType(Genre.Type.COMEDY)
//                .orElseThrow(EntityNotFoundException::new);
//
//        Genre genreHorror = genreDao.findByType(Genre.Type.HORROR)
//                .orElseThrow(EntityNotFoundException::new);
//
//        System.out.println(genreComedy);
//        System.out.println(genreHorror);

        MovieDao movieDao = new MovieDaoImpl(sessionFactory);



//        Movie movie1 = new Movie(
//                "Jak rozpętalem IIWŚ",
//                1985,
//                genreComedy
//        );
//
//        Movie movie2 = new Movie(
//                "Jak rozpętalem IIWŚ 2",
//                1988,
//                genreComedy
//        );
//
//        Movie movie3 = new Movie(
//                "Straszny Film",
//                2005,
//                genreHorror
//        );
//
//        List<Movie> movies = movieDao.saveAll(List.of(movie1, movie2, movie3));
//
//        movies.forEach(System.out::println);

//        List<Movie> allByGenreType = movieDao.findAllByGenreType(Genre.Type.COMEDY);
//        allByGenreType.forEach(System.out::println);


        DbConnection.close();
    }
}
