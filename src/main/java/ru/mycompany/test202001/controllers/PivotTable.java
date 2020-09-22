package ru.mycompany.test202001.controllers;

import org.springframework.lang.Nullable;
import ru.mycompany.test202001.repositories.PivotTableRepository;

import javax.validation.constraints.NotNull;

/**
 * @author Eugene Chernov
 */
public interface PivotTable {

    default void getPivotTable(@NotNull String rowsFieldName,
                               @Nullable String columnsFieldName,
                               @NotNull PivotTableRepository repository) {
        if (columnsFieldName == null || columnsFieldName.equals(rowsFieldName)) {
            setPivotTableWithSingleColumn(rowsFieldName);
            return;
        }
        setPivotTableWithSeveralColumns(rowsFieldName, columnsFieldName);
    }
}
