package ru.mycompany.test202001.controllers;

import org.springframework.lang.Nullable;
import ru.mycompany.test202001.dto.ElementTaxPivotTable;
import ru.mycompany.test202001.dto.PivotTableBuilder;
import ru.mycompany.test202001.repositories.PivotTableRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Eugene Chernov
 */
public interface PivotTableController {

    default List<ElementTaxPivotTable> getPivotTable(@NotNull PivotTableBuilder builder,
                                                     @NotNull PivotTableRepository repository) {
        builder.setRowsNames(repository);
        if (builder.getColumnsFieldName().equals("DefaultName")
                || builder.getColumnsFieldName().equals(builder.getRowsFieldName())) {
            setPivotTableWithSingleColumn(builder, repository);
            return builder.getTable();
        }

        builder.setColumnsNames(repository);
        setPivotTableWithSeveralColumns(builder, repository);
//        TODO: clean PivotTable

        return builder.getTable();
    }

    private void setPivotTableWithSingleColumn(
            @NotNull PivotTableBuilder builder,
            @NotNull PivotTableRepository repository
    ) {
        List<Long> valuesColumn = repository.getPivotTableColumn(builder);
        addOneColumnToPivotTable(null, valuesColumn, builder);
    }

    private void setPivotTableWithSeveralColumns(@NotNull PivotTableBuilder builder,
                                                 @NotNull PivotTableRepository repository) {
        for (String colName : builder.getColumnsNames()) {
            List<Long> valuesColumn = repository.getPivotTableColumn(colName, builder);
            addOneColumnToPivotTable(colName, valuesColumn, builder);
        }
    }

    private void addOneColumnToPivotTable(@Nullable String columnName,
                                          @NotNull List<Long> valuesColumn,
                                          @NotNull PivotTableBuilder builder) {
        if (columnName == null) {
            columnName = "Sum Value";
        }

        for (int rowNum = 0; rowNum < builder.getRowsNames().size(); rowNum++) {
            long elVal = valuesColumn.get(rowNum);
            String rowName = builder.getRowsNames().get(rowNum);
            ElementTaxPivotTable tableElement =
                    new ElementTaxPivotTable(columnName, rowName, elVal);

            builder.getTable().add(tableElement);
        }
    }
}
