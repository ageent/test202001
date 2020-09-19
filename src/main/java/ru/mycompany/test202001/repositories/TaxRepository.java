package ru.mycompany.test202001.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import ru.mycompany.test202001.domain.Tax;

import java.util.List;

/**
 * @author Eugene Chernov
 */
public interface TaxRepository extends JpaRepository<Tax, Integer>, CustomTaxRepository {
}
