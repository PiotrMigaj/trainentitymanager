package pl.migibud;

import org.hibernate.SessionFactory;
import pl.migibud.movie.MovieDao;
import pl.migibud.movie.MovieDaoImpl;

public class AppMovie {
    public static void main(String[] args) {
        SessionFactory sessionFactory = DbConnection.getSessionFactory();

        MovieDao movieDao = new MovieDaoImpl(sessionFactory);

        boolean b = movieDao.existsById(5L);

        System.out.println(b);
        DbConnection.close();
    }
}
