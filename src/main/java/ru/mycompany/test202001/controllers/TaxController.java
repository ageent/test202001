package ru.mycompany.test202001.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.mycompany.test202001.repositories.PivotTableRepository;
import ru.mycompany.test202001.dto.ElementTaxPivotTable;

import java.util.List;

/**
 * @author Eugene Chernov
 */
@RestController
public class TaxController {

    private final PivotTableRepository pivotTableRepository;

    @Autowired
    public TaxController(PivotTableRepository pivotTableRepository) {
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

    @GetMapping("/shutdown")
    public String callActuatorShutdown() {
        String url = "http://localhost:8080/actuator/shutdown";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> requestBody = new HttpEntity<>("", headers);
        String e = restTemplate.postForObject(url, requestBody, String.class);

        return "Result: " + e;
    }
}