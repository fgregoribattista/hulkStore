package todo1.dto;

import lombok.Data;

public @Data class ProductDTO {

	private Long id;

	private Long code;

	private String description;

	private BrandDTO brand;
}
