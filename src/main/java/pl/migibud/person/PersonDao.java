package pl.migibud.person;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import pl.migibud.Dao;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PersonDao implements Dao<Person,Long> {

    private final EntityManager entityManager;

    @Override
    public Person save(Person entity) {

        EntityTransaction transaction = null;

        try{
            transaction= entityManager.getTransaction();
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
    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Person.class,id));
    }

    @Override
    public List<Person> findAll() {
        return entityManager
                .createQuery("FROM Person",Person.class)
                .getResultList();
    }
}
