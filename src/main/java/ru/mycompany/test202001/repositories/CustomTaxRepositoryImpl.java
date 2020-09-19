package ru.mycompany.test202001.repositories;

import org.springframework.stereotype.Repository;
import ru.mycompany.test202001.domain.Tax;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Eugene Chernov
 */
@Repository
public class CustomTaxRepositoryImpl implements CustomTaxRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public CustomTaxRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<String> findUniqueValuesOfField(String fieldName) {
        final String strQuery = "select " + fieldName
                + " from Tax group by " + fieldName;
        TypedQuery<String> query = entityManager.createQuery(strQuery, String.class);
        return query.getResultList();
    }
}
