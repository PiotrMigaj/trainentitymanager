package pl.migibud.movie;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import pl.migibud.genre.Genre;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MovieDaoImpl implements MovieDao {

    private final SessionFactory sessionFactory;

    @Override
    public Movie save(Movie entity) {

        EntityManager entityManager = sessionFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            entityManager.persist(entity);
            transaction.commit();
            return entity;
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
                e.printStackTrace();
            }
            return null;
        }finally {
            if (entityManager!=null){
                entityManager.close();
            }
        }
    }

    @Override
    public List<Movie> saveAll(List<Movie> entities) {

        EntityManager entityManager = sessionFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            entities.forEach(entityManager::persist);
            transaction.commit();
            return Collections.unmodifiableList(entities);
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
                e.printStackTrace();
            }
            return Collections.emptyList();
        }finally {
            if (entityManager!=null){
                entityManager.close();
            }
        }
    }

    @Override
    public Optional<Movie> findById(Long id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            Optional<Movie> movie = Optional.ofNullable(entityManager.find(Movie.class, id));
            transaction.commit();
            return movie;
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
                e.printStackTrace();
            }
            return Optional.empty();
        }finally {
            if (entityManager!=null){
                entityManager.close();
            }
        }
    }

    @Override
    public List<Movie> findAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            List<Movie> movies = entityManager
                    .createQuery("FROM Movie", Movie.class)
                    .getResultList();
            transaction.commit();
            return Collections.unmodifiableList(movies);
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
                e.printStackTrace();
            }
            return Collections.emptyList();
        }finally {
            if (entityManager!=null){
                entityManager.close();
            }
        }
    }

    @Override
    public void delete(Movie entity) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            entityManager.remove(entity);
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
                e.printStackTrace();
            }
        }finally {
            if (entityManager!=null){
                entityManager.close();
            }
        }
    }

    @Override
    public Optional<Movie> findByTitle(String title) {

        EntityManager entityManager = sessionFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            Optional<Movie> movie = entityManager.createQuery("SELECT m FROM Movie m WHERE m.title=:title", Movie.class)
                    .setParameter("title", title)
                    .getResultList()
                    .stream()
                    .findFirst();
            transaction.commit();
            return movie;
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
                e.printStackTrace();
            }
            return Optional.empty();
        }finally {
            if (entityManager!=null){
                entityManager.close();
            }
        }
    }

    @Override
    public List<Movie> findAllByGenreType(Genre.Type type) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            List<Movie> movies = entityManager.createQuery("SELECT m FROM Movie m JOIN m.genre g WHERE g.type =:type", Movie.class)
                    .setParameter("type", type)
                    .getResultList();
            transaction.commit();
            return Collections.unmodifiableList(movies);
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
                e.printStackTrace();
            }
            return Collections.emptyList();
        }finally {
            if (entityManager!=null){
                entityManager.close();
            }
        }
    }

    @Override
    public void deleteAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            entityManager.createNativeQuery("DELETE FROM movies").executeUpdate();
            transaction.commit();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
                e.printStackTrace();
            }
        }finally {
            if (entityManager!=null){
                entityManager.close();
            }
        }
    }
}
