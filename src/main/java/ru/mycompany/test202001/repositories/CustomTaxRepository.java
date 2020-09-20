package ru.mycompany.test202001.repositories;

import java.util.List;

import ru.mycompany.test202001.dto.ElementTaxPivotTable;

/**
 * @author Eugene Chernov
 */
public interface CustomTaxRepository {
    List<String> findUniqueValuesOfField(String fieldName);

    List<ElementTaxPivotTable> getPivotTable(String rowsFieldName, String columnsFieldName,
                                             List<String> columnsNames);
}
