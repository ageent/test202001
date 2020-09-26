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

    static List<ElementTaxPivotTable> getPivotTable(@NotNull PivotTableBuilder builder,
                                                     @NotNull PivotTableRepository repository) {
        builder.setRowsNames(repository);
        if (builder.getColumnsFieldName().equals("DefaultName")
                || builder.getColumnsFieldName().equals(builder.getRowsFieldName())) {
            setPivotTableWithSingleColumn(builder, repository);
            return builder.getTable();
        }

        builder.setColumnsNames(repository);
        setPivotTableWithSeveralColumns(builder, repository);

        return builder.getTable();
    }

    private static void setPivotTableWithSingleColumn(
            @NotNull PivotTableBuilder builder,
            @NotNull PivotTableRepository repository
    ) {
        List<Long> valuesColumn = repository.getPivotTableColumn(builder);
        addOneColumnToPivotTable("Sum Value", valuesColumn, builder);
    }

    private static void setPivotTableWithSeveralColumns(@NotNull PivotTableBuilder builder,
                                                 @NotNull PivotTableRepository repository) {
        for (String colName : builder.getColumnsNames()) {
            List<Long> valuesColumn = repository.getPivotTableColumn(colName, builder);
            addOneColumnToPivotTable(colName, valuesColumn, builder);
        }
    }

    /*
    * param columnName is name of column in the pivot table.
    * Pass any value if the pivot table have only one column.
    * */
    private static void addOneColumnToPivotTable(@NotNull String columnName,
                                          @NotNull List<Long> valuesColumn,
                                          @NotNull PivotTableBuilder builder) {
        for (int rowNum = 0; rowNum < builder.getRowsNames().size(); rowNum++) {
            String rowName = builder.getRowsNames().get(rowNum);
            Long elVal = valuesColumn.get(rowNum);
            if (elVal == null) {
                elVal = 0L;
            }
            ElementTaxPivotTable tableElement =
                    new ElementTaxPivotTable(columnName, rowName, elVal);

            List<ElementTaxPivotTable> list = builder.getTable();
            list.add(tableElement);
        }
    }
}
