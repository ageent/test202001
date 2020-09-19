package ru.mycompany.test202001.dto;

import ru.mycompany.test202001.domain.Tax;
import ru.mycompany.test202001.domain.TaxGroup;
import ru.mycompany.test202001.domain.TaxId;
import ru.mycompany.test202001.domain.TaxLocation;

import javax.validation.constraints.NotNull;

/**
 * @author Eugene Chernov
 */
public class TaxDto extends Tax{

    /*public TaxDto() {
        super("NONE", "NONE", "NONE", "NONE", -1, 0);
    }

    public TaxDto(@NotNull TaxId id, long v) {
        super(id, v);
    }

    public TaxDto(@NotNull TaxGroup group, @NotNull TaxLocation location,
                  int y, long v) {
        super(group, location, y, v);
    }

    public TaxDto(String a, String b, String c, String d, int y, long v) {
        super(a, b, c, d, y, v);
    }

    public static Tax toDomainObject(@NotNull TaxDto dto) {
        return new Tax(dto.getA(), dto.getB(), dto.getC(), dto.getD(), dto.getY(), dto.getV());
    }

    public static TaxDto toDto(@NotNull Tax record) {
        return new TaxDto(record.getA(), record.getB(), record.getC(),
                record.getD(), record.getY(), record.getV());
    }*/
}
