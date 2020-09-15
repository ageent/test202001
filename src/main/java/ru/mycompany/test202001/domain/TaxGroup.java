package ru.mycompany.test202001.domain;

import java.util.Objects;

/**
 * @author Eugene Chernov
 */
public class TaxGroup {
    private String a;
    private String b;

    public TaxGroup() {
    }

    public TaxGroup(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "TaxGroup{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaxGroup)) return false;

        TaxGroup taxGroup = (TaxGroup) o;

        if (!Objects.equals(a, taxGroup.a)) return false;
        return Objects.equals(b, taxGroup.b);
    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        return result;
    }
}
