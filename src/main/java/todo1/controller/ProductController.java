package todo1.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todo1.KardexApplication;
import todo1.dto.ProductDTO;
import todo1.service.ProductService;
import java.net.URI;
import java.util.List;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/v1/products")
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(KardexApplication.class);

	@Autowired
	private ProductService productService;

	/**
	 * Get products
	 * @return list of products
	 */
	@GetMapping("")
	public ResponseEntity<Object> getAll(){
		List<ProductDTO> productDTOList = productService.getAll();
		return ResponseEntity.ok(productDTOList);
	}

	/**
	 * Get product by id
	 * @param id
	 * @return ProductDTO
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable long id) {
		ProductDTO productDto = productService.getById(id);
		return ResponseEntity.ok(productDto);
	}

	/**
	 * add product
	 * @param productDTO
	 * @return new productDTO
	 */
	@PostMapping("")
	public ResponseEntity<Object> add(@Valid @RequestBody ProductDTO productDTO){
		log.info("Add product");
		//add product
		ProductDTO respuestaProductDTO = productService.add(productDTO);
		//add location to response
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(productDTO.getId())
				.toUri();
		return ResponseEntity.created(location).body(respuestaProductDTO);
	}

	/**
	 * update product
	 * @param id producto
	 * @param productDTO
	 * @return list of products
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable long id, @Valid @RequestBody ProductDTO productDTO){
		log.info("Update product "+ id);
		//update product
		ProductDTO respuestaProductDTO = productService.update(id, productDTO);
		return ResponseEntity.ok(respuestaProductDTO);
	}

	/**
	 * Delete product by id
	 * @param id
	 * @return No Content => satus 204
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable long id) {
		log.info("Delete product "+ id);
	    productService.delete(id);
		ResponseEntity.noContent();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
