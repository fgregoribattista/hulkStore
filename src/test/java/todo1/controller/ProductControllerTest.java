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

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

import todo1.dto.ProductDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /*
     * Se genera una peticion GET al listado de productos
     * Se espera una respuesta exitosa (200)
     */
    @Test
    public void httpTestGet() throws Exception {
        final String baseUrl = "http://localhost:" + port + "/api/v1/products";
        URI uri = new URI(baseUrl);
        //send request
        ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);
        assertEquals(200, result.getStatusCodeValue());
    }

    /*
     * Se genera una peticion GET a un producto existente
     * Se espera una respuesta exitosa (200)
     */
    @Test
    public void httpTestView() throws Exception {
        //set uri and ID 5 => corresponds to initial product load
        final String baseUrl = "http://localhost:" + port + "/api/v1/products/5";
        URI uri = new URI(baseUrl);
        //send request
        ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);
        assertEquals(200, result.getStatusCodeValue());
    }

    /*
     * Se genera una peticion GET a un producto NO existente
     * Se espera una respuesta del tipo Not Found (404)
     */
    @Test
    public void httpTestViewFail() throws Exception {
        //set uri and ID 155 => corresponds to NOT EXIST product
        final String baseUrl = "http://localhost:" + port + "/api/v1/products/150";
        URI uri = new URI(baseUrl);
        //send request
        ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);
        assertEquals(404, result.getStatusCodeValue());
    }

    /*
     * Se genera una peticion POST creando un pruducto nuevo
     * Se espera una respuesta exitosa => created (201)
     */
    @Test
    public void httpTestAddSuccess() throws Exception {
        final String baseUrl = "http://localhost:" + port + "/api/v1/products";
        URI uri = new URI(baseUrl);
        //set productDTO to test
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDescription("testAddProduct");
        productDTO.setCode(100L);
        productDTO.setBrandId(1L);
        //set header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<ProductDTO> request = new HttpEntity<>(productDTO, headers);
        //send request
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        //expect 201 => created
        assertEquals(201, result.getStatusCodeValue());
    }

    /*
     * Se genera una peticion POST creando un pruducto nuevo con una marca inexistente
     * Se espera una respuesta del tipo Not Found (404)
     */
    @Test
    public void httpTestAddFailBrand() throws Exception {
        final String baseUrl = "http://localhost:" + port + "/api/v1/products";
        URI uri = new URI(baseUrl);
        //set productDTO to test
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDescription("testAddProduct");
        productDTO.setCode(100L);
        //set NOT EXIST brand
        productDTO.setBrandId(100L);
        //set header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<ProductDTO> request = new HttpEntity<>(productDTO, headers);
        //send request
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        //expect 404 => notFound brand
        assertEquals(404, result.getStatusCodeValue());
    }

    /*
     * Se genera una peticion POST creando un pruducto nuevo con una descripción ya existente en otro producto
     * Se espera una respuesta del tipo Bad Request (400)
     */
    @Test
    public void httpTestAddFailDescriptionExist() throws Exception {
        final String baseUrl = "http://localhost:" + port + "/api/v1/products";
        URI uri = new URI(baseUrl);
        //set productDTO to test
        ProductDTO productDTO = new ProductDTO();
        //set EXIST description product
        productDTO.setDescription("VASO");
        productDTO.setCode(1L);
        productDTO.setBrandId(1L);
        //set header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<ProductDTO> request = new HttpEntity<>(productDTO, headers);
        //send request
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        //expect 400 => badRequest
        assertEquals(400, result.getStatusCodeValue());
    }

    /*
     * Se genera una peticion POST creando un pruducto nuevo con un identificador propio
     * Se espera una respuesta del tipo Bad Request (400)
     */
    @Test
    public void httpTestAddFailId() throws Exception {
        final String baseUrl = "http://localhost:" + port + "/api/v1/products";
        URI uri = new URI(baseUrl);
        //set productDTO to test
        ProductDTO productDTO = new ProductDTO();
        //set ID
        productDTO.setId(100L);
        productDTO.setDescription("VASO");
        productDTO.setCode(1L);
        productDTO.setBrandId(1L);
        //set header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<ProductDTO> request = new HttpEntity<>(productDTO, headers);
        //send request
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        //expect 400 => badRequest
        assertEquals(400, result.getStatusCodeValue());
    }

    /*
     * Se genera una peticion PUT modificando un pruducto ya existente
     * Se espera una respuesta exitosa (200)
     */
    @Test
    public void httpTestModSuccess() throws Exception {
        //set uri and ID 5 => corresponds to initial product load
        final String baseUrl = "http://localhost:" + port + "/api/v1/products/5";
        URI uri = new URI(baseUrl);
        //set productDTO to test
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDescription("VASO-PEQUEÑO");
        productDTO.setCode(1L);
        productDTO.setBrandId(1L);
        //set header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<ProductDTO> request = new HttpEntity<>(productDTO, headers);
        //send request
        ResponseEntity<String> result = this.restTemplate.exchange(uri, HttpMethod.PUT, request, String.class);
        //expect 200 => success
        assertEquals(200, result.getStatusCodeValue());
    }

    /*
     * Se genera una peticion PUT modificando un pruducto ya existente con una marca inexistente
     * Se espera una respuesta del tipo NotFound (404)
     */
    @Test
    public void httpTestModFailBrand() throws Exception {
        //set uri and ID 5 => corresponds to initial product load
        final String baseUrl = "http://localhost:" + port + "/api/v1/products/5";
        URI uri = new URI(baseUrl);
        //set productDTO to test
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDescription("VASO-PEQUEÑO");
        productDTO.setCode(1L);
        //set NOT EXIST brand id
        productDTO.setBrandId(100L);
        //set header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<ProductDTO> request = new HttpEntity<>(productDTO, headers);
        //send request
        ResponseEntity<String> result = this.restTemplate.exchange(uri, HttpMethod.PUT, request, String.class);
        //expect 404 => Not Found Brand
        assertEquals(404, result.getStatusCodeValue());
    }

    /*
     * Se genera una peticion PUT modificando un pruducto inexistente
     * Se espera una respuesta del tipo NotFound (404)
     */
    @Test
    public void httpTestModFailId() throws Exception {
        //set uri and ID 5 => NOT EXIST ID
        final String baseUrl = "http://localhost:" + port + "/api/v1/products/150";
        URI uri = new URI(baseUrl);
        //set productDTO to test
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDescription("VASO-PEQUEÑO");
        productDTO.setCode(1L);
        productDTO.setBrandId(1L);
        //set header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<ProductDTO> request = new HttpEntity<>(productDTO, headers);
        //send request
        ResponseEntity<String> result = this.restTemplate.exchange(uri, HttpMethod.PUT, request, String.class);
        //expect 404 => Not Found Product
        assertEquals(404, result.getStatusCodeValue());
    }

    /*
     * Se genera una peticion DELETE eliminando un producto previamente cargado sin stock
     * Se espera una respuesta exitosa sin body (204)
     */
    @Test
    public void httpTestDeleteSuccess() throws Exception {
        //set uri and ID 31 => corresponds to initial product load with out stock
        final String baseUrl = "http://localhost:" + port + "/api/v1/products/31";
        URI uri = new URI(baseUrl);
        //set header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<ProductDTO> request = new HttpEntity<>(headers);
        //send request
        ResponseEntity<String> result = this.restTemplate.exchange(uri, HttpMethod.DELETE, request, String.class);
        //expect 204 => Not Content
        assertEquals(204, result.getStatusCodeValue());
    }

    /*
     * Se genera una peticion DELETE eliminando un producto previamente cargado con stock
     * Se espera una respuesta no aceptable (406)
     */
    @Test
    public void httpTestDeleteFailWithStock() throws Exception {
        //set uri and ID 5 => corresponds to initial product load with stock > 0
        final String baseUrl = "http://localhost:" + port + "/api/v1/products/5";
        URI uri = new URI(baseUrl);
        //set header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<ProductDTO> request = new HttpEntity<>(headers);
        //send request
        ResponseEntity<String> result = this.restTemplate.exchange(uri, HttpMethod.DELETE, request, String.class);
        //expect 406 => Not Acceptable
        assertEquals(406, result.getStatusCodeValue());
    }

    /*
     * Se genera una peticion DELETE eliminando un producto inexistente
     * Se espera una respuesta Not Found (404)
     */
    @Test
    public void httpTestDeleteFailNotExistProduct() throws Exception {
        //set uri and ID 150 => Not exist product with id 150
        final String baseUrl = "http://localhost:" + port + "/api/v1/products/150";
        URI uri = new URI(baseUrl);
        //set header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<ProductDTO> request = new HttpEntity<>(headers);
        //send request
        ResponseEntity<String> result = this.restTemplate.exchange(uri, HttpMethod.DELETE, request, String.class);
        //expect 404 => Not Found Product
        assertEquals(404, result.getStatusCodeValue());
    }


}
