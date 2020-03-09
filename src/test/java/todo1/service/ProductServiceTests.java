package todo1.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import todo1.exceptions.ProductNotFoundException;
import todo1.model.Brand;
import todo1.model.Product;
import todo1.repository.ProductRepository;
import java.util.Optional;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTests {

	@InjectMocks
	private ProductService productService;

	@Mock
	private ProductRepository repository;

	@BeforeEach
	void setMock() {
		Brand brandDC = new Brand();
		brandDC.setId(1L);
		brandDC.setDescription("DC Comics");

		Optional<Product> product = Optional.of(new Product());

		product.map(
				x -> {
					x.setCode(2L);
					x.setBrand(brandDC);
					x.setDescription("CAMISETA");
					x.setId(22L);
					return x;
				});

		when(repository.findById(22L)).thenReturn(product);

		Optional<Product> product2 = Optional.of(new Product());

		product2.map(
				x -> {
					x.setCode(26L);
					x.setBrand(new Brand());
					x.setDescription("VASO");
					x.setId(44L);
					return x;
				});

		when(repository.findById(44L)).thenReturn(product2);

	}

	/*
	 * Se fuerza búsqueda de producto con id inválido
	 * Se espera excepcion ProductNotFoundException
	 */
	@Test
	public void getByIdWithProductNotFoundException() {

		Assertions.assertThrows(ProductNotFoundException.class, () -> {

			 productService.getById(99L);

		});
	}

}
