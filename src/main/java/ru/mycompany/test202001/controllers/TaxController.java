package ru.mycompany.test202001.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.mycompany.test202001.repositories.TaxRepository;

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

    public
}
