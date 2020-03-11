package todo1.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductExistException extends RuntimeException {

    private static final Logger log = LoggerFactory.getLogger(ProductExistException.class);

    public ProductExistException(String description, String brandDescription) {
        super("El Producto con nombre " + description + " y marca " + brandDescription + " ya existe en el sistema.");
        log.error("El Producto con nombre " + description + " y marca " + brandDescription + " ya existe en el sistema.");
    }
}
