package todo1.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class StockQuantityException extends RuntimeException {

    private static final Logger log = LoggerFactory.getLogger(StockQuantityException.class);

    public StockQuantityException(Long id) {
        super("El Producto " +id+ " se encuentra con stock");
        log.error("El Producto " +id+ " se encuentra con stock");
    }

    public StockQuantityException(String message, Throwable cause) {
        super(message, cause);
    }
}
