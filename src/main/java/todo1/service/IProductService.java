package todo1.service;

import todo1.dto.ProductDTO;
import java.util.List;

public interface IProductService {

	public ProductDTO getById(long id);
	
	public List<ProductDTO> getAll();

}
