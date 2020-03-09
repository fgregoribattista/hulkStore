package todo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todo1.dto.ProductDTO;
import todo1.service.ProductService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/v1/products")
public class ProductController {

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

}
