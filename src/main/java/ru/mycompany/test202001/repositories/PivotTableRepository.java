package ru.mycompany.test202001.repositories;

import java.util.List;

import ru.mycompany.test202001.dto.ElementTaxPivotTable;

/**
 * @author Eugene Chernov
 */
public interface PivotTableRepository {
    List<String> findUniqueValuesOfField(String fieldName);

    void setPivotTable(String rowsFieldName, String columnsFieldName);

    List<ElementTaxPivotTable> getPivotTable();
}
