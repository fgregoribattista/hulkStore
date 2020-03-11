package todo1.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todo1.dto.ProductDTO;
import todo1.dto.BrandDTO;
import todo1.exceptions.ProductExistException;
import todo1.exceptions.ProductExistIdException;
import todo1.exceptions.ProductNotFoundException;
import todo1.exceptions.BrandNotFoundException;
import todo1.model.Product;
import todo1.model.Stock;
import todo1.model.Brand;
import todo1.repository.ProductRepository;
import todo1.repository.BrandRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private StockService stockService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get all productos
     *
     * @return List<ProductDTO>
     */
    public List<ProductDTO> getAll() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product : productList) {
            ProductDTO productDTO = new ProductDTO();
            modelMapper.map(product, productDTO);

            productDTOList.add(productDTO);
        }

        return productDTOList;
    }

    /**
     * Get product by id
     *
     * @param id id Product
     * @return ProductDTO
     * @throws Exception when not found product
     */
    public ProductDTO getById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        ProductDTO productDTO = new ProductDTO();
        modelMapper.map(product, productDTO);

        return productDTO;
    }
    /**
     * Save product
     * @param  productDTO
     * @return ProductDTO
     * @throws Exception when not found product
     * @throws Exception when not found brand
     */
    public ProductDTO add(ProductDTO productDTO) {
        //validate ID product
        if (productDTO.getId() != null) {
            throw new ProductExistIdException(productDTO.getBrandId());
        }
        //validate exist brand
        Brand brand = brandRepository.findById(productDTO.getBrandId()).orElseThrow(() -> new BrandNotFoundException(productDTO.getBrandId()));
        //validate exist product
        Product productExist = productRepository.findByDescriptionAndBrand(productDTO.getDescription(), brand);
        if (productExist != null) {
            throw new ProductExistException(productExist.getDescription(), productExist.getBrand().getDescription());
        }
        //save new product with initial stock
        Stock stock = stockService.saveDataStock(stockService.setDataToSave(productDTO, brand));
        //set id product in productDto
        productDTO.setId(stock.getProduct().getId());
        //set brandDTO in productDTO
        BrandDTO brandDTO = new BrandDTO();
        modelMapper.map(brand, brandDTO);
        productDTO.setBrand(brandDTO);
        return productDTO;
    }
    /**
     * Update product
     * @param  productDTO
     * @return ProductDTO
     * @throws Exception when not found product
     * @throws Exception when not found brand
     */

    public ProductDTO update(long id, ProductDTO productDTO) {
        //validate exist product
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        //validate brand
        Brand brand = brandRepository.findById(productDTO.getBrandId()).orElseThrow(() -> new BrandNotFoundException(productDTO.getBrandId()));
        //set product data
        product.setDescription(productDTO.getDescription());
        product.setCode(productDTO.getCode());
        product.setBrand(brand);
        product.setId(id);
        //update product
        productRepository.save(product);
        //map model to DTO to response
        modelMapper.map(product, productDTO);
        return productDTO;
    }
    /**
     * Update product
     * @param  id Product
     * @throws Exception when not found product
     * @throws Exception when not found brand
     * @throws Exception when not found stock
     */

    public void delete(long id) {
        //validate exist product
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        stockService.deleteStockOfProduct(product);
    }

}
