package ru.mycompany.test202001.repositories;

import org.springframework.stereotype.Repository;
import ru.mycompany.test202001.dto.ElementTaxPivotTable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugene Chernov
 */
@Repository
public class CustomTaxRepositoryForPivotTableImpl implements CustomTaxRepositoryForPivotTable {

    @PersistenceContext
    private EntityManager entityManager;
    private String rowsFieldName = "DefaultName";       // Field name of rows of pivot table
    private String columnsFieldName = "DefaultName";    // Field name of columns of pivot table
    private List<String> columnsNames = List.of("DefaultColumnName");
    private List<String> rowsNames = List.of("DefaultRowName");

    public CustomTaxRepositoryForPivotTableImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<String> findUniqueValuesOfField(String fieldName) {
        final String strQuery = "select " + fieldName
                + " from Tax group by " + fieldName;
        TypedQuery<String> query = entityManager.createQuery(strQuery, String.class);
        return query.getResultList();
    }

    @Override
    public List<ElementTaxPivotTable> getPivotTable(String rowsFieldName, String columnsFieldName) {
        fillBean(rowsFieldName, columnsFieldName);
        int pivotTableSize = rowsNames.size() * columnsNames.size();
        List<ElementTaxPivotTable> pivotTable = new ArrayList<>(pivotTableSize);

        for (String colName : columnsNames) {
            List<Long> valuesColumn = getPivotTableSumField(colName);

            for (int rowNum = 0; rowNum < rowsNames.size(); rowNum++) {
                assert valuesColumn != null;
                long elVal = valuesColumn.get(rowNum);
                String rowName = rowsNames.get(rowNum);
                ElementTaxPivotTable elTable =
                        new ElementTaxPivotTable(colName, rowName, elVal);

                pivotTable.add(elTable);
            }
        }

        return pivotTable;
    }

    public void fillBean(String rowsFieldName, String columnsFieldName) {
        setRowsFieldName(rowsFieldName);
        setColumnsFieldName(columnsFieldName);
        setRowsNames(findUniqueValuesOfField(rowsFieldName));
        setColumnsNames(findUniqueValuesOfField(columnsFieldName));
    }

    /*
    * param columnNumber is column name of pivot table.
    * */
    private List<Long> getPivotTableSumField(String columnName) {
        final String strQuery = "select sum(case when " + columnsFieldName
                + " = '" + columnName + "' then v end) from Tax group by " + rowsFieldName;
        TypedQuery<Long> query = entityManager.createQuery(strQuery, Long.class);

        return query.getResultList();
    }

    public String getRowsFieldName() {
        return rowsFieldName;
    }

    public void setRowsFieldName(String rowsFieldName) {
        this.rowsFieldName = rowsFieldName;
    }

    public String getColumnsFieldName() {
        return columnsFieldName;
    }

    public void setColumnsFieldName(String columnsFieldName) {
        this.columnsFieldName = columnsFieldName;
    }

    public List<String> getColumnsNames() {
        return columnsNames;
    }

    public void setColumnsNames(List<String> columnsNames) {
        this.columnsNames = columnsNames;
    }

    public List<String> getRowsNames() {
        return rowsNames;
    }

    public void setRowsNames(List<String> rowsNames) {
        this.rowsNames = rowsNames;
    }
}
