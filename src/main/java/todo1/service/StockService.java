package todo1.service;

import todo1.dto.MovementDTO;
import todo1.dto.StockDTO;
import todo1.exceptions.StockOutException;
import todo1.exceptions.StockNotFoundException;
import todo1.repository.ProductRepository;
import todo1.repository.StockRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todo1.model.Stock;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService implements IStockService{

	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	private static int inMovement = 1;

	private static int outMovement = 2;

	/**
	 * add movement to stock
	 * @param id id Stock
	 * @throws StockNotFoundException if stock (by id) not exist
	 * @throws Exception when movement generate quantity < 0
	 */
	@Override
	public StockDTO addMovement(long id, MovementDTO movementDTO) {
		//Get stock by id, if not exist trow exception
		Stock stock = stockRepository.findById(id)
						.orElseThrow(() -> new StockNotFoundException(id));

		//validate is movment in or out
		if(movementDTO.getType() == inMovement) {
			stock.setQuantity(stock.getQuantity() + movementDTO.getQuantity());
		} else if(movementDTO.getType()== outMovement) {
			long existencia = stock.getQuantity() - movementDTO.getQuantity();
			//Vvalidate stock
			if(existencia < 0) {
				throw new StockOutException(stock.getProduct().getId());
			}
			//set quantity to stock
			stock.setQuantity(existencia);
		}

		stockRepository.save(stock);
		
		//update stockDTO to response
		StockDTO stockDTO = new StockDTO();
		stockDTO.setQuantity(stock.getQuantity());
		stockDTO.setId(stock.getId());

//		modelMapper.map(stock, stockDTO);

		return stockDTO;
	}

	/**
	 * Get all Stocks
	 * @return List<StockDTO>
	 */
	@Override
	public List<StockDTO> getAll() {
		List<Stock> stockList = stockRepository.findAll();
		List<StockDTO> stockDTOList = new ArrayList<>();
		for (Stock stock:stockList) {
			StockDTO stockDTO = new StockDTO();
			modelMapper.map(stock, stockDTO);

			stockDTOList.add(stockDTO);
		}
		
		return stockDTOList;
	}

	/**
	 * Get stock by id
	 * @param id
	 * @throws Exception when not found product
	 * @return stockDTO
	 */
	@Override
	public StockDTO getById(long id) {
		//get stock by id or throw exception
		Stock stock = stockRepository.findById(id)
				.orElseThrow(() -> new StockNotFoundException(id));
		//map stock to stockDTO
		StockDTO stockDTO = new StockDTO();
		modelMapper.map(stock, stockDTO);
		return stockDTO;
	}
	
}
