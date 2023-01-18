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

        MovieDao movieDao = new MovieDaoImpl(sessionFactory);

        boolean b = movieDao.existsById(5L);

        System.out.println(b);
        DbConnection.close();
    }
}
