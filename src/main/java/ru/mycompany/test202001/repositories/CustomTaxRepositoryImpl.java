package ru.mycompany.test202001.repositories;

import org.springframework.stereotype.Repository;
import ru.mycompany.test202001.domain.Tax;
import ru.mycompany.test202001.dto.ElementTaxPivotTable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Arrays;
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
//        TODO: remove ORDER BY
        final String strQuery = "select " + fieldName
                + " from Tax group by " + fieldName
                + " order by " + fieldName;
        TypedQuery<String> query = entityManager.createQuery(strQuery, String.class);
        return query.getResultList();
    }

    @Override
    public List<ElementTaxPivotTable> getPivotTable(String rowsFieldName, String columnsFieldName,
                                                    List<String> columnsNames) {
//        final String strQuery = "select rowsFieldName, " +
//                "sum(case when columnsFieldName = 'A' then v end), ";


//        TypedQuery<String> query = entityManager.createQuery(strQuery, String.class);
//        return query.getResultList();
        return null;
    }

    private static String buildStrQueryToGetPivotTable(String rowsFieldName, String columnsFieldName,
                                                       List<String> columnsNames) {
        StringBuilder builder = new StringBuilder();

        builder.append("select ");
        builder.append(rowsFieldName);
        builder.append(", ");

        builder.append("sum(case when columnsFieldName = '");
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

    public static void main(String[] args) {
//        Test method buildStrQueryToGetPivotTable
        System.out.println(buildStrQueryToGetPivotTable("rowsField",
                "columnsField", List.of("c1", "c2", "c3")));
    }
}
