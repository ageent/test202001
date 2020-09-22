package ru.mycompany.test202001.controllers;

import org.springframework.lang.Nullable;
import ru.mycompany.test202001.domain.Tax;
import ru.mycompany.test202001.dto.ElementTaxPivotTable;
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
        builder.setRowsNames(repository);
        if (builder.getColumnsFieldName().equals("DefaultName")
                || builder.getColumnsFieldName().equals(builder.getRowsFieldName())) {
            setPivotTableWithSingleColumn(builder, repository);
            return;
        }

        builder.setColumnsNames(repository);
        setPivotTableWithSeveralColumns(builder, repository);
//        TODO: clean PivotTable
    }

    private void setPivotTableWithSingleColumn(@NotNull PivotTableBuilder builder,
                                               @NotNull PivotTableRepository repository) {
        addOneColumnToPivotTable(repository.getPivotTableColumn(builder));
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

    private void setPivotTableWithSeveralColumns(@NotNull PivotTableBuilder builder,
                                                 @NotNull PivotTableRepository repository) {
        for (String colName : columnsNames) {
            List<Long> valuesColumn = getPivotTableSumField(colName);
            addOneColumnToPivotTable(valuesColumn);
        }
    }
}
