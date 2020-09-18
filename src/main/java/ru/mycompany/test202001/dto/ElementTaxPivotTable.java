package ru.mycompany.test202001.dto;

import java.util.Objects;

/**
 * @author Eugene Chernov
 */
public class ElementTaxPivotTable<C, R, V extends Number> {
        private C col;
        private R row;
        private V val;

        public ElementTaxPivotTable(C col, R row, V val) {
                this.col = col;
                this.row = row;
                this.val = val;
        }

        public C getCol() {
                return col;
        }

        public void setCol(C col) {
                this.col = col;
        }

        public R getRow() {
                return row;
        }

        public void setRow(R row) {
                this.row = row;
        }

        public V getVal() {
                return val;
        }

        public void setVal(V val) {
                this.val = val;
        }

        @Override
        public String toString() {
                return "ElementTaxPivotTable{" +
                        "col=" + col +
                        ", row=" + row +
                        ", val=" + val +
                        '}';
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof ElementTaxPivotTable)) return false;

                ElementTaxPivotTable<?, ?, ?> that = (ElementTaxPivotTable<?, ?, ?>) o;

                if (!Objects.equals(col, that.col)) return false;
                if (!Objects.equals(row, that.row)) return false;
                return Objects.equals(val, that.val);
        }

        @Override
        public int hashCode() {
                int result = col != null ? col.hashCode() : 0;
                result = 31 * result + (row != null ? row.hashCode() : 0);
                result = 31 * result + (val != null ? val.hashCode() : 0);
                return result;
        }
}
