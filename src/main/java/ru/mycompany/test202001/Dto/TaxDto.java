package ru.mycompany.test202001.Dto;

import ru.mycompany.test202001.domain.Tax;
import ru.mycompany.test202001.domain.TaxGroup;
import ru.mycompany.test202001.domain.TaxId;
import ru.mycompany.test202001.domain.TaxLocation;

import javax.validation.constraints.NotNull;

/**
 * @author Eugene Chernov
 */
public class TaxDto {
    private String a = "NONE";
    private String b = "NONE";
    private String c = "NONE";
    private String d = "NONE";
    private String y = "NONE";
    private String v;

    public TaxDto() {
    }

    public TaxDto(@NotNull TaxId id, String v) {
        this.a = id.getA();
        this.b = id.getB();
        this.c = id.getC();
        this.d = id.getD();
        this.y = id.getY();
        this.v = v;
    }

    public TaxDto(@NotNull TaxGroup group, @NotNull TaxLocation location,
               String y, String v) {
        this.a = group.getA();
        this.b = group.getB();
        this.c = location.getC();
        this.d = location.getD();
        this.y = y;
        this.v = v;
    }

    public TaxDto(String a, String b, String c, String d, String y, String v) {
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

    public static Tax toDomainObject(@NotNull TaxDto dto) {
        return new Tax(dto.getA(), dto.getB(), dto.getC(), dto.getD(), dto.getY(), dto.getV());
    }

    public static TaxDto toDto(@NotNull Tax record) {
        return new TaxDto(record.getA(), record.getB(), record.getC(),
                record.getD(), record.getY(), record.getV());
    }
}
