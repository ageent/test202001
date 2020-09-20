package ru.mycompany.test202001.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mycompany.test202001.repositories.CustomTaxRepositoryForPivotTable;
import ru.mycompany.test202001.dto.ElementTaxPivotTable;

import java.util.List;

/**
 * @author Eugene Chernov
 */
@RestController
public class TaxController {

    private final CustomTaxRepositoryForPivotTable pivotTableRepository;

    @Autowired
    public TaxController(CustomTaxRepositoryForPivotTable pivotTableRepository) {
        this.pivotTableRepository = pivotTableRepository;
    }

    @GetMapping("/")
    public List<ElementTaxPivotTable> getPivotTable(
            @RequestParam("row") String row,
            @RequestParam("col") String col
    ) {
//        TODO: if row = col
        return pivotTableRepository.getPivotTable(row, col);
    }

//    @Mapping("/error")
//    public
}
// TODO: tests
// TODO: /error
// TODO: if row = col