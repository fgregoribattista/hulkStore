package todo1.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductExistIdException extends RuntimeException {

    private static final Logger log = LoggerFactory.getLogger(ProductExistIdException.class);

    public ProductExistIdException(Long id) {
        super("El Producto a guardar no puede tener identificador: " +id);
        log.error("El Producto a guardar no puede tener identificador: " +id);
    }
}
