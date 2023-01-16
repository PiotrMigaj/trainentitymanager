package pl.migibud;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.SessionFactory;
import pl.migibud.genre.Genre;
import pl.migibud.genre.GenreDao;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        SessionFactory sessionFactory = DbConnection.getSessionFactory();

        GenreDao genreDao = new GenreDao(sessionFactory.createEntityManager());
        Genre genre1 = new Genre("ACTION");
        Genre genre2 = new Genre("FANTASY");
        Genre genre3 = new Genre("HORROR");
        List<Genre> genres1 = new ArrayList<>();
        genres1.add(genre1);
        genres1.add(genre2);
        genres1.add(genre3);
        List<Genre> genres = genreDao.saveAll(List.of(genre1,genre2,genre3));
        System.out.println(genres.size());

        Genre genre = genreDao.findByName("ACTION")
                .orElseThrow(() -> new EntityNotFoundException("NO ENTITY WITH ACTION"));

        System.out.println("Genre:" +genre);

        Genre genrexD = genreDao.findByName("DUPA")
                .orElseThrow(() -> new EntityNotFoundException("NO ENTITY WITH DUPA"));

        DbConnection.close();
    }
}
