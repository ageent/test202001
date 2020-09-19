package ru.mycompany.test202001.repositories;

import java.util.List;

/**
 * @author Eugene Chernov
 */
public interface CustomTaxRepository {
    List<String> findUniqueValuesOfField(String fieldName);
}
