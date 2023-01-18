package pl.migibud;

import java.util.List;
import java.util.Optional;

public interface Dao<T,ID> {
    T save(T entity);
    List<T> saveAll(List<T> entities);
    Optional<T> findById(ID id);
    List<T> findAll();
    void delete(T entity);
    void deleteAll();
    boolean existsById(Long id);
}
