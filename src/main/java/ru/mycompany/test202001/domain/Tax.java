package ru.mycompany.test202001.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Eugene Chernov
 */
@Entity
@Table(name = "source_data")
@IdClass(TaxId.class)
public class Tax {

    @Id
    @AttributeOverrides({
            @AttributeOverride(name = "a",
                    column = @Column(name = "A")),
            @AttributeOverride(name = "b",
                    column = @Column(name = "B")),
            @AttributeOverride(name = "c",
                    column = @Column(name = "C")),
            @AttributeOverride(name = "d",
                    column = @Column(name = "D")),
            @AttributeOverride(name = "y",
                    column = @Column(name = "Y"))
    })
    private String a;
    private String b;
    private String c;
    private String d;
    private String y;

    private long v;

    public Tax() {
    }

    public Tax(@NotNull TaxId id, long v) {
        this.a = id.getA();
        this.b = id.getB();
        this.c = id.getC();
        this.d = id.getD();
        this.y = id.getY();
        this.v = v;
    }

    public Tax(@NotNull TaxGroup group, @NotNull TaxLocation location,
               String y, long v) {
        this.a = group.getA();
        this.b = group.getB();
        this.c = location.getC();
        this.d = location.getD();
        this.y = y;
        this.v = v;
    }

    public Tax(String a, String b, String c,
               String d, String y, long v) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.y = y;
        this.v = v;
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

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public long getV() {
        return v;
    }

    public void setV(long v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "Tax{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", y='" + y + '\'' +
                ", v='" + v + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tax)) return false;

        Tax tax = (Tax) o;

        if (v != tax.v) return false;
        if (!Objects.equals(a, tax.a)) return false;
        if (!Objects.equals(b, tax.b)) return false;
        if (!Objects.equals(c, tax.c)) return false;
        if (!Objects.equals(y, tax.y)) return false;
        return Objects.equals(d, tax.d);
    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        result = 31 * result + (c != null ? c.hashCode() : 0);
        result = 31 * result + (d != null ? d.hashCode() : 0);
        result = 31 * result + (y != null ? y.hashCode() : 0);
        result = 31 * result + (int) (v ^ (v >>> 32));
        return result;
    }
}
