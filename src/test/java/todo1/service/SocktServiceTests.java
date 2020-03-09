package todo1.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import todo1.dto.StockDTO;
import todo1.dto.MovementDTO;
import todo1.exceptions.StockOutException;
import todo1.exceptions.StockNotFoundException;
import todo1.model.Brand;
import todo1.model.Product;
import todo1.model.Stock;
import todo1.repository.StockRepository;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SocktServiceTests {

	@InjectMocks
	private StockService stockService;
	@Mock
	private StockRepository repository;

	private static int inMovement = 1;

	private static int outMovement = 2;

	@BeforeEach
	void setMock() {
		Brand brand = new Brand();
		brand.setId(1L);
		brand.setDescription("DC Comics");


		Product product = new Product();
		product.setCode(2L);
		product.setBrand(new Brand());
		product.setDescription("CAMISETA");
		product.setId(22L);
		product.setBrand(brand);

		Optional<Stock> stockIn = Optional.of(new Stock());

		stockIn.map(
				x -> {
					x.setQuantity(0L);
					x.setProduct(product);
					x.setId(1L);
					return x;
				});

		when(repository.findById(1L)).thenReturn(stockIn);

		Optional<Stock> stockOut = Optional.of(new Stock());

		stockOut.map(
				x -> {
					x.setQuantity(5L);
					x.setId(2L);
					return x;
				});

		when(repository.findById(2L)).thenReturn(stockOut);
			}


	/*
	 * Stock inicial en 0. Se intenta generar un movimiento incrementando a 3 el stock.
	 * Se espera devolucion exacta sin excepciones
	 */

	@Test
	public void addMovementAdditionSucess() {

		MovementDTO movementDTO = new MovementDTO();
		movementDTO.setType(inMovement);
		movementDTO.setQuantity(3L);
		
		try {
			StockDTO stockDTO = stockService.addMovement(1L, movementDTO);
			assertEquals(3L, stockDTO.getQuantity().longValue());

		} catch (StockOutException | StockNotFoundException out) {
			fail();
		} catch (Exception e) {
			fail();
		}

	}
	/*
	 * Stock inicial en 5. Se intenta generar un movimiento decrementando a 2 el stock.
	 * Se espera devolucion exitosa sin excepciones
	 */
	@Test
	public void addMovementSubtractionSucces() {

		MovementDTO movementDTO = new MovementDTO();
		movementDTO.setType(outMovement);
		movementDTO.setQuantity(3L);


		try {
			StockDTO stockDTO = stockService.addMovement(2L, movementDTO);
			assertEquals(2L, stockDTO.getQuantity().longValue());

		} catch (StockOutException | StockNotFoundException out) {
			fail();
		} catch (Exception e) {
			fail();
		}

	}

	/*
	 * Stock inicial en 0. Se intenta generar un movimiento decrementando el stock a un valor invalido.
	 * Se espera excepcion StockOutException
	 */
	@Test
	public void addMovementWithOutOfStockException() {

		Assertions.assertThrows(StockOutException.class, () -> {

			MovementDTO movementDTO = new MovementDTO();
			movementDTO.setType(outMovement);
			movementDTO.setQuantity(3L);
			
			StockDTO stockDTO = stockService.addMovement(1L, movementDTO);
			assertEquals(2L, stockDTO.getQuantity().longValue());

		});
	}

	/*
	 * Stock inicial en 0. Se intenta generar un movimiento con id erroneo de stock.
	 * Se espera excepcion StockNotFoundException
	 */
	@Test
	public void addMovementWithStockNotFoundException() {

		Assertions.assertThrows(StockNotFoundException.class, () -> {

			MovementDTO movementDTO = new MovementDTO();
			movementDTO.setType(outMovement);
			movementDTO.setQuantity(3L);
			
			StockDTO stockDTO = stockService.addMovement(4L,movementDTO);
			assertEquals(2L, stockDTO.getQuantity().longValue());

		});
	}

	/*
	 * Se fuerza búsqueda de producto con id inválido
	 * Se espera excepcion StockNotFoundException
	 */
	@Test
	public void getByIdWithProductNotFoundException() {

		Assertions.assertThrows(StockNotFoundException.class, () -> {

			stockService.getById(99L);

		});
	}
}
