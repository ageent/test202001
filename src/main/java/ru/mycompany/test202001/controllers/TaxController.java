package ru.mycompany.test202001.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mycompany.test202001.domain.Tax;
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
    public String getPivotTable(String row, String col) {
        List<Tax> taxes = repository.findAll();
        System.out.println(taxes);
        return taxes.toString();
    }
}
