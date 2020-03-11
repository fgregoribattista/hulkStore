package todo1.service;

import todo1.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    public ProductDTO getById(long id);

    public List<ProductDTO> getAll();

    public ProductDTO update(long id, ProductDTO productDTO);

    public void delete(long id);

    public ProductDTO add(ProductDTO productDTO);

}
