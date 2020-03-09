package todo1.controller;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import todo1.dto.MovementDTO;
import todo1.dto.StockDTO;
import todo1.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/v1/stocks")
public class StockController {

	@Autowired
	private StockService stockService;

	/**
	 * Get Stocks
	 * @return list of stocks
	 */
	@GetMapping("")
	public ResponseEntity<Object> get(){
		List<StockDTO> stockDTOList = stockService.getAll();
		return ResponseEntity.ok(stockDTOList);
	}

	/**
	 * Get Stock by id
	 * @param id Stock
	 * @return stock object
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> view(@PathVariable long id){
		StockDTO stockDTO = stockService.getById(id);
		return ResponseEntity.ok(stockDTO);
	}

	/**
	 * Add movement to stock
	 * @param id Stock , object movementDTO
	 * @return stockDto
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Object> modStock(@PathVariable long id, @Valid @RequestBody MovementDTO movementDTO) {
		//add movement
		StockDTO stockDTO  = stockService.addMovement(id, movementDTO);

		return ResponseEntity.ok(stockDTO);

	}
}
