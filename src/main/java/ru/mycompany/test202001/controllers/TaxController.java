package ru.mycompany.test202001.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
        return pivotTableRepository.getPivotTable(row, col);
//        List<String> uniqueColumns = pivotTableRepository.findUniqueValuesOfField(col);
//        return uniqueColumns;
    }
}
