package ru.mycompany.test202001;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mycompany.test202001.repositories.CustomTaxRepositoryForPivotTable;

/**
 * @author Eugene Chernov
 */
//TODO: json entity != json entity
//TODO: sum json of values = sum table of values
//TODO: count json entities
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomTaxRepositoryForPivotTable repository;


}
