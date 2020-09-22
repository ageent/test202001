package ru.mycompany.test202001.controllers;

import org.springframework.lang.Nullable;
import ru.mycompany.test202001.dto.PivotTableBuilder;
import ru.mycompany.test202001.repositories.PivotTableRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Eugene Chernov
 */
public interface PivotTableController {

    default void getPivotTable(@NotNull PivotTableBuilder builder,
                               @NotNull PivotTableRepository repository) throws IllegalArgumentException {
        if (builder.getRowsFieldName().equals("DefaultName")) {
            throw new IllegalArgumentException("Empty PivotTableBuilder.");
        }
        builder.setRowsNames(repository);

        if (builder.getColumnsFieldName().equals("DefaultName")
                || builder.getColumnsFieldName().equals(builder.getRowsFieldName())) {
            setPivotTableWithSingleColumn(builder);
            return;
        }
        builder.setColumnsNames(repository);
        setPivotTableWithSeveralColumns(builder);
    }

    private void setPivotTableWithSingleColumn(@NotNull PivotTableBuilder builder) {
        addOneColumnToPivotTable(getPivotTableSumField());
    }

    private void setPivotTableWithSeveralColumns(@NotNull PivotTableBuilder builder) {
        for (String colName : columnsNames) {
            List<Long> valuesColumn = getPivotTableSumField(colName);
            addOneColumnToPivotTable(valuesColumn);
        }
    }
}
