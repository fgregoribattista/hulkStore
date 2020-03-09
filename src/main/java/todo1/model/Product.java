package todo1.model;

import lombok.Data;

import javax.persistence.*;

@Entity()
public @Data class Product {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "code", nullable = false, length = 50)
	private Long code;
	
	@Column(name = "description", nullable = false, length = 100)
	private String description;

	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "brand_id")
	private Brand brand;
	
	
}
