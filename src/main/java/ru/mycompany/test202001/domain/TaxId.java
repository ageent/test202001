package ru.mycompany.test202001.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Eugene Chernov
 */
@Embeddable
public class TaxId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String a;
    private String b;
    private String c;
    private String d;
    private int y;

    public TaxId() {
    }

    public TaxId(@NotNull TaxGroup group, @NotNull TaxLocation location,
                 int y) {
        this.a = group.getA();
        this.b = group.getB();
        this.c = location.getC();
        this.d = location.getD();
        this.y = y;
    }

    public TaxId(String a, String b, String c, String d, int y) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.y = y;
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

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "TaxId{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", y='" + y + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaxId)) return false;

        TaxId taxId = (TaxId) o;

        if (y != taxId.y) return false;
        if (!Objects.equals(a, taxId.a)) return false;
        if (!Objects.equals(b, taxId.b)) return false;
        if (!Objects.equals(c, taxId.c)) return false;
        return Objects.equals(d, taxId.d);
    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        result = 31 * result + (c != null ? c.hashCode() : 0);
        result = 31 * result + (d != null ? d.hashCode() : 0);
        result = 31 * result + y;
        return result;
    }
}
