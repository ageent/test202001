package ru.mycompany.test202001.repositories;

import org.springframework.stereotype.Repository;
import ru.mycompany.test202001.dto.ElementTaxPivotTable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Eugene Chernov
 */
@Repository
public class CustomTaxRepositoryForPivotTableImpl implements CustomTaxRepositoryForPivotTable {

    @PersistenceContext
    private EntityManager entityManager;
    private String rowsFieldName = "DefaultName";
    private String columnsFieldName = "DefaultName";
    private List<String> columnsNames = List.of("DefaultColumnName");
    private List<String> rowsNames = List.of("DefaultRowName");

    public CustomTaxRepositoryForPivotTableImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<String> findUniqueValuesOfField(String fieldName) {
//        TODO: remove ORDER BY
        final String strQuery = "select " + fieldName
                + " from Tax group by " + fieldName
                + " order by " + fieldName;
        TypedQuery<String> query = entityManager.createQuery(strQuery, String.class);
        return query.getResultList();
    }

    @Override
    public List<ElementTaxPivotTable> getPivotTable(String rowsFieldName, String columnsFieldName) {
//        final String strQuery =
//                buildStrQueryToGetPivotTable(rowsFieldName, columnsFieldName, columnsNames);
        fillBean(rowsFieldName, columnsFieldName);


//        TypedQuery<String> query = entityManager.createQuery(strQuery, String.class);
//        return query.getResultList();
        return null;
    }

    public void fillBean(String rowsFieldName, String columnsFieldName) {
        setRowsFieldName(rowsFieldName);
        setColumnsFieldName(columnsFieldName);
        setRowsNames(findUniqueValuesOfField(rowsFieldName));
        setColumnsNames(findUniqueValuesOfField(columnsFieldName));
    }

    private List<Long> getPivotTableSumField() {
//        final String strQuery = "select sum(case when " + ;
//        TypedQuery<Long> query = entityManager.createQuery(strQuery, Long.class);

        return null;
    }

    private static String buildStrQueryToGetPivotTable(String rowsFieldName, String columnsFieldName,
                                                       List<String> columnsNames) {
        StringBuilder builder = new StringBuilder();

        builder.append("select ");
        builder.append(rowsFieldName);
        builder.append(", ");

        builder.append("sum(case when ");
        builder.append(columnsFieldName);
        builder.append(" = '");
        builder.append(columnsNames.get(0));
        builder.append("' then v end)");
        for (int columnsCount = 1; columnsCount < columnsNames.size(); columnsCount++) {
            builder.append(", ");
            builder.append("sum(case when ");
            builder.append(columnsFieldName);
            builder.append(" = '");
            builder.append(columnsNames.get(columnsCount));
            builder.append("' then v end)");
        }

//        TODO: remove ORDER BY
        builder.append(" from Tax");
        builder.append(" group by ");
        builder.append(rowsFieldName);
        builder.append(" order by ");
        builder.append(rowsFieldName);
        builder.append(";");

        return builder.toString();
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

    public static void main(String[] args) {
//        Test method buildStrQueryToGetPivotTable
        System.out.println(buildStrQueryToGetPivotTable("rowsField",
                "columnsField", List.of("c1", "c2", "c3")));
    }
}
