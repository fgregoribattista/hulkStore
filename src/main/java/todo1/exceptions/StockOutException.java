package todo1.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class StockOutException extends RuntimeException {

    private static final Logger log = LoggerFactory.getLogger(StockOutException.class);

    public StockOutException(Long id) {
        super("El Producto " +id+ " se encuentra sin stock");
        log.error("El Producto " +id+ " se encuentra sin stock");
    }

    public StockOutException(String message, Throwable cause) {
        super(message, cause);
    }
}
