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
                               @NotNull PivotTableRepository repository) {
        if (columnsFieldName == null || columnsFieldName.equals(rowsFieldName)) {
            setPivotTableWithSingleColumn(rowsFieldName);
            return;
        }
        setPivotTableWithSeveralColumns(rowsFieldName, columnsFieldName);
    }

    private void setPivotTableWithSingleColumn(@NotNull String rowsFieldName) {
        fillBean(rowsFieldName);

        addOneColumnToPivotTable(getPivotTableSumField());
    }

    private void setPivotTableWithSeveralColumns(@NotNull String rowsFieldName,
                                                @NotNull String columnsFieldName) {
        fillBean(rowsFieldName, columnsFieldName);

        for (String colName : columnsNames) {
            List<Long> valuesColumn = getPivotTableSumField(colName);
            addOneColumnToPivotTable(valuesColumn);
        }
    }
}
