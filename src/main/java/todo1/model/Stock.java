package todo1.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity()
public @Data class Stock {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;


	@Column(name = "quantity", nullable = false, length = 5)
	private Long quantity;

}
