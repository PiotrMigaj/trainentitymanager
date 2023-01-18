package pl.migibud.genre;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class GenreDaoImpl implements GenreDao {

    private final SessionFactory sessionFactory;

    @Override
    public Genre save(Genre entity) {

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
    public List<Genre> saveAll(List<Genre> entities) {

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
    public Optional<Genre> findById(Long id) {

        EntityManager entityManager = sessionFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            Optional<Genre> genre = Optional.ofNullable(entityManager.find(Genre.class, id));
            transaction.commit();
            return genre;
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
    public Optional<Genre> findByType(Genre.Type type){

        EntityManager entityManager = sessionFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            Optional<Genre> optionalGenre = entityManager.createQuery("SELECT g FROM Genre g WHERE g.type = :type", Genre.class)
                    .setParameter("type", type)
                    .getResultStream()
                    .findFirst();
            transaction.commit();
            return optionalGenre;
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
    public List<Genre> findAll() {

        EntityManager entityManager = sessionFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            List<Genre> genres = entityManager
                    .createQuery("FROM Genre", Genre.class)
                    .getResultList();
            transaction.commit();
            return Collections.unmodifiableList(genres);
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
    public void delete(Genre entity) {

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
}
