package ru.mycompany.test202001.domain;

import javax.persistence.*;

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
                    column = @Column(name="A")),
            @AttributeOverride(name = "b",
                    column = @Column(name="B")),
            @AttributeOverride(name = "c",
                    column = @Column(name="C")),
            @AttributeOverride(name = "d",
                    column = @Column(name="D")),
            @AttributeOverride(name = "y",
                    column = @Column(name="Y"))
    })
    private String a;
    private String b;
    private String c;
    private String d;
    private String y;

    private String v;

    public Tax() {
    }

    public Tax(String a, String b, String c,
                 String d, String y, String v) {
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

    public String getV() {
        return v;
    }

    public void setV(String v) {
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
}
