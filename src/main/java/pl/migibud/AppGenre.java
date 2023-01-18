package pl.migibud;

import org.hibernate.SessionFactory;
import pl.migibud.genre.Genre;
import pl.migibud.genre.GenreDao;
import pl.migibud.genre.GenreDaoImpl;

import java.util.List;

public class AppGenre {

    public static void main(String[] args) {
        SessionFactory sessionFactory = DbConnection.getSessionFactory();

        GenreDao genreDao = new GenreDaoImpl(sessionFactory);

        Genre genre1 = new Genre(Genre.Type.COMEDY);
        Genre genre2 = new Genre(Genre.Type.HORROR);
        Genre genre3 = new Genre(Genre.Type.ROMANCE);

        List<Genre> genres = genreDao.saveAll(List.of(genre1,genre2,genre3));
        System.out.println(genres.size());

        DbConnection.close();
    }
}
