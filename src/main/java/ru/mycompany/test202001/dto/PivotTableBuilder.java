package ru.mycompany.test202001.dto;

import org.springframework.lang.Nullable;
import ru.mycompany.test202001.repositories.PivotTableRepository;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Eugene Chernov
 */
public class PivotTableBuilder {

    /*Entity is a class of an entity of a DB*/
    private Class<?> entity;
    /*Field name of an entity of a DB. This field keep values for the aggregation*/
    private String valuesFieldName;
    /*Field name of rows of pivot table*/
    private String rowsFieldName;
    /*Field name of columns of pivot table*/
    private String columnsFieldName = "DefaultName";
    private List<String> columnsNames = List.of("DefaultColumnName");
    private List<String> rowsNames = List.of("DefaultRowName");
    private List<ElementTaxPivotTable> table = new ArrayList<>();

    public PivotTableBuilder(@NotNull String rowsFieldName,
                             @NotNull String valuesFieldName,
                             @NotNull Class<?> entity) {
        this.rowsFieldName = rowsFieldName;
        this.valuesFieldName = valuesFieldName;
        this.entity = entity;
    }

    public PivotTableBuilder(@NotNull String rowsFieldName,
                             @Nullable String columnsFieldName,
                             @NotNull String valuesFieldName,
                             @NotNull Class<?> entity) {
        this.rowsFieldName = rowsFieldName;
        this.valuesFieldName = valuesFieldName;
        this.entity = entity;
        if (columnsFieldName != null) {
            this.columnsFieldName = columnsFieldName;
        }
    }

    public void setColumnsNames(@NotNull PivotTableRepository repository) {
        this.columnsNames = repository.findUniqueValuesOfField(columnsFieldName, this);
    }

    public void setRowsNames(@NotNull PivotTableRepository repository) {
        this.rowsNames = repository.findUniqueValuesOfField(rowsFieldName, this);
    }

    public Class<?> getEntity() {
        return entity;
    }

    public void setEntity(Class<?> entity) {
        this.entity = entity;
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

    public List<ElementTaxPivotTable> getTable() {
        return table;
    }

    public void setTable(List<ElementTaxPivotTable> table) {
        this.table = table;
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

        PivotTableBuilder builder = (PivotTableBuilder) o;

        if (!Objects.equals(entity, builder.entity)) return false;
        if (!Objects.equals(valuesFieldName, builder.valuesFieldName)) return false;
        if (!Objects.equals(rowsFieldName, builder.rowsFieldName)) return false;
        if (!Objects.equals(columnsFieldName, builder.columnsFieldName)) return false;
        if (!Objects.equals(columnsNames, builder.columnsNames)) return false;
        if (!Objects.equals(rowsNames, builder.rowsNames)) return false;
        return Objects.equals(table, builder.table);
    }

    @Override
    public int hashCode() {
        int result = entity != null ? entity.hashCode() : 0;
        result = 31 * result + (valuesFieldName != null ? valuesFieldName.hashCode() : 0);
        result = 31 * result + (rowsFieldName != null ? rowsFieldName.hashCode() : 0);
        result = 31 * result + (columnsFieldName != null ? columnsFieldName.hashCode() : 0);
        result = 31 * result + (columnsNames != null ? columnsNames.hashCode() : 0);
        result = 31 * result + (rowsNames != null ? rowsNames.hashCode() : 0);
        result = 31 * result + (table != null ? table.hashCode() : 0);
        return result;
    }
}
