package ru.mycompany.test202001.dto;

import java.util.Objects;

/**
 * @author Eugene Chernov
 */
public class ElementTaxPivotTable {
        private String row;
        private String col;
        private long val;

        public ElementTaxPivotTable(String col, String row, long val) {
                this.row = row;
                this.col = col;
                this.val = val;
        }

        public String getCol() {
                return col;
        }

        public void setCol(String col) {
                this.col = col;
        }

        public String getRow() {
                return row;
        }

        public void setRow(String row) {
                this.row = row;
        }

        public long getVal() {
                return val;
        }

        public void setVal(long val) {
                this.val = val;
        }

        @Override
        public java.lang.String toString() {
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

                ElementTaxPivotTable that = (ElementTaxPivotTable) o;

                if (val != that.val) return false;
                if (!Objects.equals(row, that.row)) return false;
                return Objects.equals(col, that.col);
        }

        @Override
        public int hashCode() {
                int result = row != null ? row.hashCode() : 0;
                result = 31 * result + (col != null ? col.hashCode() : 0);
                result = 31 * result + (int) (val ^ (val >>> 32));
                return result;
        }
}
