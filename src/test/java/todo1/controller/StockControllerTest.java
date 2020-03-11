package todo1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import todo1.dto.MovementDTO;
import todo1.dto.ProductDTO;
import todo1.dto.StockDTO;

import java.net.URI;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StockControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /*
     * Se genera una peticion GET al listado de stock
     * Se espera una respuesta exitosa (200)
     */
    @Test
    public void httpTestGet() throws Exception {
        final String baseUrl = "http://localhost:" + port + "/api/v1/stocks";
        URI uri = new URI(baseUrl);
        //send request
        ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);
        assertEquals(200, result.getStatusCodeValue());
    }
    /*
     * Se genera una peticion GET a un determinado stock existente
     * Se espera una respuesta exitosa (200)
     */
    @Test
    public void httpTestView() throws Exception {
        //set uri and ID 4 => corresponds to initial stock load
        final String baseUrl = "http://localhost:" + port + "/api/v1/stocks/4";
        URI uri = new URI(baseUrl);
        //send request
        ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);
        assertEquals(200, result.getStatusCodeValue());
    }
    /*
     * Se genera una peticion GET a un determinado stock inexistente
     * Se espera una respuesta NotFound (404)
     */
    @Test
    public void httpTestViewFail() throws Exception {
        //set uri and ID 140 => corresponds to NOT EXIST stock
        final String baseUrl = "http://localhost:" + port + "/api/v1/stocks/140";
        URI uri = new URI(baseUrl);
        //send request
        ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);
        assertEquals(404, result.getStatusCodeValue());
    }
    /*
     * Se genera una peticion PUT modificando el stock de un producto existente
     * Se espera una respuesta exitosa (200)
     */
    @Test
    public void httpTestModSuccess() throws Exception {
        //set uri and ID 4 => corresponds to initial stock load
        final String baseUrl = "http://localhost:" + port + "/api/v1/stocks/4";
        URI uri = new URI(baseUrl);
        //set stockDTO to test
        MovementDTO movementDTO = new MovementDTO();
        movementDTO.setQuantity(2L);
        movementDTO.setType(1);
        //set header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<MovementDTO> request = new HttpEntity<>(movementDTO, headers);
        //send request
        ResponseEntity<String> result = this.restTemplate.exchange(uri, HttpMethod.PUT, request, String.class);
        //expect 200 => success
        assertEquals(200, result.getStatusCodeValue());
    }
    /*
     * Se genera una peticion PUT modificando el stock de un producto existente
     * generando un movimiento de sustraccion con una cantidad mayor a la del stock
     * Se espera una respuesta no aceptable (406)
     */
    @Test
    public void httpTestModFailStock() throws Exception {
        //set uri and ID 4 => corresponds to initial product load with 100 quantity
        final String baseUrl = "http://localhost:" + port + "/api/v1/stocks/4";
        URI uri = new URI(baseUrl);
        //set stockDTO to test
        MovementDTO movementDTO = new MovementDTO();
        //set 200 quantity
        movementDTO.setQuantity(200L);
        movementDTO.setType(2);
        //set header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<MovementDTO> request = new HttpEntity<>(movementDTO, headers);
        //send request
        ResponseEntity<String> result = this.restTemplate.exchange(uri, HttpMethod.PUT, request, String.class);
        //expect 406 => not aceptable
        assertEquals(406, result.getStatusCodeValue());
    }
    /*
     * Se genera una peticion PUT modificando el stock de un producto inexistente
     * Se espera una respuesta not found (404)
     */
    @Test
    public void httpTestModFailStockFound() throws Exception {
        //set uri and ID 140 => corresponds to NOT EXIST stock
        final String baseUrl = "http://localhost:" + port + "/api/v1/stocks/140";
        URI uri = new URI(baseUrl);
        //set stockDTO to test
        MovementDTO movementDTO = new MovementDTO();
        //set 200 quantity
        movementDTO.setQuantity(200L);
        movementDTO.setType(2);
        //set header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<MovementDTO> request = new HttpEntity<>(movementDTO, headers);
        //send request
        ResponseEntity<String> result = this.restTemplate.exchange(uri, HttpMethod.PUT, request, String.class);
        //expect 404 => not found
        assertEquals(404, result.getStatusCodeValue());
    }

}
