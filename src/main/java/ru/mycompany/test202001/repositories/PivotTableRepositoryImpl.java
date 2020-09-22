package ru.mycompany.test202001.repositories;

import org.springframework.stereotype.Repository;
import ru.mycompany.test202001.dto.ElementTaxPivotTable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugene Chernov
 */
@Repository
public class PivotTableRepositoryImpl implements PivotTableRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private String rowsFieldName = "DefaultName";       // Field name of rows of pivot table
    private String columnsFieldName = "DefaultName";    // Field name of columns of pivot table
    private List<String> columnsNames = List.of("DefaultColumnName");
    private List<String> rowsNames = List.of("DefaultRowName");
    private List<ElementTaxPivotTable> pivotTable = new ArrayList<>();

    public PivotTableRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<String> findUniqueValuesOfField(String fieldName) {
        final String strQuery = "select " + fieldName
                + " from Tax group by " + fieldName;
        TypedQuery<String> query = entityManager.createQuery(strQuery, String.class);
        return query.getResultList();
    }

/*
    @Override
    public void setPivotTable(@NotNull String rowsFieldName,
                              @Nullable String columnsFieldName) {
        if (columnsFieldName == null || columnsFieldName.equals(rowsFieldName)) {
            setPivotTableWithSingleColumn(rowsFieldName);
            return;
        }
        setPivotTableWithSeveralColumns(rowsFieldName, columnsFieldName);
    }
*/

    /**
     * Sets all fields and cleans Pivot Table
     * */
    public void fillBean(@NotNull String rowsFieldName, @NotNull String columnsFieldName) {
        setRowsFieldName(rowsFieldName);
        setColumnsFieldName(columnsFieldName);
        setRowsNames(findUniqueValuesOfField(rowsFieldName));
        setColumnsNames(findUniqueValuesOfField(columnsFieldName));
        setPivotTable(new ArrayList<>());
    }

    /**
     * Sets some fields and cleans Pivot Table
     * */
    public void fillBean(@NotNull String rowsFieldName) {
        setRowsFieldName(rowsFieldName);
        setColumnsFieldName("DefaultName");
        setRowsNames(findUniqueValuesOfField(rowsFieldName));
        setColumnsNames(List.of("DefaultColumnName"));
        setPivotTable(new ArrayList<>());
    }

    public void setPivotTableWithSingleColumn(@NotNull String rowsFieldName) {
        fillBean(rowsFieldName);

        addOneColumnToPivotTable(getPivotTableSumField());
    }

    public void setPivotTableWithSeveralColumns(@NotNull String rowsFieldName,
                                                @NotNull String columnsFieldName) {
        fillBean(rowsFieldName, columnsFieldName);

        for (String colName : columnsNames) {
            List<Long> valuesColumn = getPivotTableSumField(colName);
            addOneColumnToPivotTable(valuesColumn);
        }
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

    @Override
    public List<ElementTaxPivotTable> getPivotTable() {
        return pivotTable;
    }

    public void setPivotTable(List<ElementTaxPivotTable> pivotTable) {
        this.pivotTable = pivotTable;
    }

    private void addOneColumnToPivotTable(List<Long> valuesColumn) {
        String colName = "V";

        for (int rowNum = 0; rowNum < rowsNames.size(); rowNum++) {
            long elVal = valuesColumn.get(rowNum);
            String rowName = rowsNames.get(rowNum);
            ElementTaxPivotTable elTable =
                    new ElementTaxPivotTable(colName, rowName, elVal);

            pivotTable.add(elTable);
        }
    }

    /*
     * param columnNumber is column name of pivot table.
     * */
    private List<Long> getPivotTableSumField(String columnName) {
        final String strQuery = "select sum(case when " + columnsFieldName
                + " = '" + columnName + "' then v end) from Tax group by " + rowsFieldName;
        TypedQuery<Long> query = entityManager.createQuery(strQuery, Long.class);

        List<Long> res = query.getResultList();
        assert res.size() == rowsNames.size();
        return res;
    }

    private List<Long> getPivotTableSumField() {
        final String strQuery = "select sum(v) from Tax group by " + rowsFieldName;
        TypedQuery<Long> query = entityManager.createQuery(strQuery, Long.class);

        List<Long> res = query.getResultList();
        assert res.size() == rowsNames.size();
        return res;
    }
}
