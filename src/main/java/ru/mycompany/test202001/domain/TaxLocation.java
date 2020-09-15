package ru.mycompany.test202001.domain;

import java.util.Objects;

/**
 * @author Eugene Chernov
 */
public class TaxLocation {
    private String c;
    private String d;

    public TaxLocation() {
    }

    public TaxLocation(String c, String d) {
        this.c = c;
        this.d = d;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "TaxLocation{" +
                "c='" + c + '\'' +
                ", d='" + d + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaxLocation)) return false;

        TaxLocation that = (TaxLocation) o;

        if (!Objects.equals(c, that.c)) return false;
        return Objects.equals(d, that.d);
    }

    @Override
    public int hashCode() {
        int result = c != null ? c.hashCode() : 0;
        result = 31 * result + (d != null ? d.hashCode() : 0);
        return result;
    }
}
