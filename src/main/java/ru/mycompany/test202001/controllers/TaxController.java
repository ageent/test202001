package ru.mycompany.test202001.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mycompany.test202001.domain.Tax;
import ru.mycompany.test202001.dto.ElementTaxPivotTable;
import ru.mycompany.test202001.dto.TaxDto;
import ru.mycompany.test202001.repositories.TaxRepository;

import java.util.List;

/**
 * @author Eugene Chernov
 */
@RestController
public class TaxController {

    private final TaxRepository repository;

    @Autowired
    public TaxController(TaxRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public List<String> getPivotTable(
//            @RequestParam("row") String row,
            @RequestParam("col") String col
    ) {
        List<String> uniqueColumns = repository.findUniqueValuesOfField(col);
        return uniqueColumns;
    }
}
