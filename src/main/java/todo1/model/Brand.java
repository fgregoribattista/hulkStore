package todo1.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity()
public @Data class Brand {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "description", nullable = false, length = 100)
	private String description;

}
