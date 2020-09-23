package ru.mycompany.test202001.repositories;

import org.springframework.stereotype.Repository;
import ru.mycompany.test202001.dto.PivotTableBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Eugene Chernov
 */
@Repository
public class PivotTableRepositoryImpl implements PivotTableRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public PivotTableRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<String> findUniqueValuesOfField(String fieldName,
                                                @NotNull PivotTableBuilder builder) {
        final String strQuery = "select " + fieldName + " from "
                + builder.getEntity().getName() + " group by " + fieldName;
        TypedQuery<String> query = entityManager.createQuery(strQuery, String.class);
        return query.getResultList();
    }

    /**
     * @param columnName is column name of pivot table.
     * */
    public List<Long> getPivotTableColumn(String columnName,
                                          @NotNull PivotTableBuilder builder) {
        final String strQuery = "select sum(case when " + builder.getColumnsFieldName()
                + " = '" + columnName + "' then " + builder.getValuesFieldName()
                + " end) from " + builder.getEntity().getName() + " group by "
                + builder.getRowsFieldName();
        TypedQuery<Long> query = entityManager.createQuery(strQuery, Long.class);

        List<Long> res = query.getResultList();
        assert res.size() == builder.getRowsNames().size();
        return res;
    }

    public List<Long> getPivotTableColumn(@NotNull PivotTableBuilder builder) {
        final String strQuery = "select sum(" + builder.getValuesFieldName()
                + ") from " + builder.getEntity().getName() + " group by "
                + builder.getRowsFieldName();
        TypedQuery<Long> query = entityManager.createQuery(strQuery, Long.class);

        List<Long> res = query.getResultList();
        assert res.size() == builder.getRowsNames().size();
        return res;
    }
}
