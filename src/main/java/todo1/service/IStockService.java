package todo1.service;

import todo1.dto.MovementDTO;
import todo1.dto.StockDTO;
import todo1.model.Product;
import todo1.model.Stock;

import java.util.List;

public interface IStockService {

	public List<StockDTO> getAll();

	public StockDTO getById(long id);

	public StockDTO addMovement(long id, MovementDTO movementDTO);

	public void deleteStockOfProduct(Product product);

	public Stock saveDataStock(Stock stock);
}
