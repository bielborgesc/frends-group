package database.dao;

import java.util.List;

public interface GenericDAO<T, K> {
    void save(T t);
    void update(T t);
    void delete(K k);
    T load(K k);
    List<T> loadAll();
}
