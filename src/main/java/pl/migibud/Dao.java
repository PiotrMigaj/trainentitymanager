package pl.migibud;

import java.util.List;
import java.util.Optional;

public interface Dao<T,ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
}
