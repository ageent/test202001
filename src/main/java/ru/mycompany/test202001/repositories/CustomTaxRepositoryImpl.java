package ru.mycompany.test202001.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Eugene Chernov
 */
@Repository
public class CustomTaxRepositoryImpl implements CustomTaxRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> findUniqueValuesOfField(String fieldName) {
        
    }
}
