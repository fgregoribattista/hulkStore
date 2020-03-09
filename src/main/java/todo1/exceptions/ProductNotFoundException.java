package todo1.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductNotFoundException extends RuntimeException {

	private static final Logger log = LoggerFactory.getLogger(ProductNotFoundException.class);

	public ProductNotFoundException(Long id) {
		super("El Producto con el identificador: "+id +" no existe.");
		log.error("El Producto con el identificador: "+id +" no existe.");
	}
}
