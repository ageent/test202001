package ru.mycompany.test202001.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.mycompany.test202001.domain.Tax;

import java.util.List;

/**
 * @author Eugene Chernov
 */
public interface TaxRepository extends CrudRepository<Tax, Integer> {
    List<Tax> findAll();
}
