package org.EduRegisterationSystem.Data;

import java.util.List;
import java.util.function.Predicate;

public interface Repository<T> {
    List<T> getAll();

    void setAll(List<T> entities);

    void add(T entity);

    T remove(int index);

    T get(int index);

    T findBy(Predicate<T> filter) throws Exception;
    boolean contains(Predicate<T> filter);
    void set(int index, T entity);
}
