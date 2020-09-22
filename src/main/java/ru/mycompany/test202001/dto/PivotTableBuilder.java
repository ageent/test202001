package ru.mycompany.test202001.dto;

import org.springframework.lang.Nullable;
import ru.mycompany.test202001.repositories.PivotTableRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * @author Eugene Chernov
 */
public class PivotTableBuilder {

    /*Field name in an entity of a DB. This field keep values for the aggregation*/
    private String valuesFieldName;
    /*Field name of rows of pivot table*/
    private String rowsFieldName;
    /*Field name of columns of pivot table*/
    private String columnsFieldName = "DefaultName";
    private List<String> columnsNames = List.of("DefaultColumnName");
    private List<String> rowsNames = List.of("DefaultRowName");
    private List<ElementTaxPivotTable> table =
            List.of(new ElementTaxPivotTable("DefaultName", "DefaultName", -1L));

    public PivotTableBuilder(@NotNull String rowsFieldName,
                             @NotNull String valuesFieldName) {
        this.rowsFieldName = rowsFieldName;
        this.valuesFieldName = valuesFieldName;
    }

    public PivotTableBuilder(@NotNull String rowsFieldName,
                             @NotNull String valuesFieldName,
                             @Nullable String columnsFieldName) {
        this.rowsFieldName = rowsFieldName;
        this.valuesFieldName = valuesFieldName;
        if (columnsFieldName != null) {
            this.columnsFieldName = columnsFieldName;
        }
    }

    public void setColumnsNames(@NotNull PivotTableRepository repository) {
        this.columnsNames = repository.findUniqueValuesOfField(columnsFieldName);
    }

    public void setRowsNames(@NotNull PivotTableRepository repository) {
        this.columnsNames = repository.findUniqueValuesOfField(rowsFieldName);
    }

    public String getValuesFieldName() {
        return valuesFieldName;
    }

    public void setValuesFieldName(String valuesFieldName) {
        this.valuesFieldName = valuesFieldName;
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

    @Override
    public String toString() {
        return "PivotTableBuilder{" +
                "rowsFieldName='" + rowsFieldName + '\'' +
                ", columnsFieldName='" + columnsFieldName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PivotTableBuilder)) return false;

        PivotTableBuilder that = (PivotTableBuilder) o;

        if (!Objects.equals(rowsFieldName, that.rowsFieldName)) return false;
        if (!Objects.equals(columnsFieldName, that.columnsFieldName)) return false;
        if (!Objects.equals(columnsNames, that.columnsNames)) return false;
        if (!Objects.equals(rowsNames, that.rowsNames)) return false;
        return Objects.equals(table, that.table);
    }

    @Override
    public int hashCode() {
        int result = rowsFieldName != null ? rowsFieldName.hashCode() : 0;
        result = 31 * result + (columnsFieldName != null ? columnsFieldName.hashCode() : 0);
        result = 31 * result + (columnsNames != null ? columnsNames.hashCode() : 0);
        result = 31 * result + (rowsNames != null ? rowsNames.hashCode() : 0);
        result = 31 * result + (table != null ? table.hashCode() : 0);
        return result;
    }
}
