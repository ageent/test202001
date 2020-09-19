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

    @Query("select y from Tax group by y")
//    @Query("select :fieldName from Tax group by :fieldName")
//    @Query("select :fieldName from Tax group by a")
//    @Query("select a from Tax group by :fieldName")
    List<String> findUniqueValuesOfField(String fieldName);
}
