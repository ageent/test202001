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


    /**
     * @param col may be null or equals param row
     * */
    @GetMapping("/")
    public List<ElementTaxPivotTable> getPivotTable(
            @RequestParam("row") String row,
            @RequestParam(value = "col", required = false) String col
    ) {
        pivotTableRepository.setPivotTable(row, col);
        return pivotTableRepository.getPivotTable();
    }

//    @Mapping("/error")
//    public
}
// TODO: tests
// TODO: /error
// TODO: if row = col