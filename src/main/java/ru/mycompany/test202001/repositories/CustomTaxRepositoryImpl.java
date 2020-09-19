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
        TypedQuery<String> query = entityManager.createQuery(
                "select :fieldName from Tax group by :fieldName",
                String.class
        );
        query.setParameter("fieldName", fieldName);
        /*TypedQuery<String> query = entityManager.createQuery(
                "select a from Tax group by a",
                String.class
        );*/
        return query.getResultList();
    }
}
