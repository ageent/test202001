package ru.mycompany.test202001.repositories;

import java.util.List;

import ru.mycompany.test202001.dto.ElementTaxPivotTable;
import ru.mycompany.test202001.dto.PivotTableBuilder;

import javax.validation.constraints.NotNull;

/**
 * @author Eugene Chernov
 */
public interface PivotTableRepository {
    List<String> findUniqueValuesOfField(String fieldName);

    List<Long> getPivotTableColumn(String columnName,
                                   @NotNull PivotTableBuilder builder);

    List<Long> getPivotTableColumn(@NotNull PivotTableBuilder builder);
}
