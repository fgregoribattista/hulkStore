package todo1.dto;

import lombok.Data;

import java.util.List;

public @Data class StockDTO {

	private Long id;
	
	private String description;
	
	private Long quantity;

	private ProductDTO product;

}
