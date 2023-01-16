package pl.migibud.genre;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import pl.migibud.Dao;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class GenreDao implements Dao<Genre,Long> {

    private final EntityManager entityManager;

    @Override
    public Genre save(Genre entity) {

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
            }
        }
        return null;
    }

    @Override
    public List<Genre> saveAll(List<Genre> entities) {

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
            }
        }
        return Collections.emptyList();
    }


    @Override
    public Optional<Genre> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Genre.class,id));
    }

    public Optional<Genre> findByName(String name){
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            if (!transaction.isActive()){
                transaction.begin();
            }
            Optional<Genre> optionalGenre = entityManager.createQuery("SELECT g FROM Genre g WHERE g.name = :name", Genre.class)
                    .setParameter("name", name)
                    .getResultStream()
                    .findFirst();
            transaction.commit();
            return optionalGenre;
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Genre> findAll() {
        return entityManager
                .createQuery("FROM Genre",Genre.class)
                .getResultList();
    }

    @Override
    public void delete(Genre entity) {

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
            }
        }
    }
}
