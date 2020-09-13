package ru.mycompany.test202001.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

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
    private String y;

    public TaxId() {
    }

    public TaxId(String a, String b, String c, String d, String y) {
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

    public String getY() {
        return y;
    }

    public void setY(String y) {
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
}
