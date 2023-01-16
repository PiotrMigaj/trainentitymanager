package pl.migibud;

import org.hibernate.SessionFactory;
import pl.migibud.person.Person;
import pl.migibud.person.PersonDao;

import java.util.List;

public class App {

    public static void main(String[] args) {
        SessionFactory sessionFactory = DbConnection.getSessionFactory();
        Dao<Person,Long> personDao = new PersonDao(sessionFactory.createEntityManager());

        Person piotr = new Person("Piotr","Migaj");
        Person anna = new Person("Anna","Migaj");

        personDao.save(piotr);
        personDao.save(anna);

        List<Person> all = personDao.findAll();

        all.forEach(System.out::println);

        DbConnection.close();
    }
}
