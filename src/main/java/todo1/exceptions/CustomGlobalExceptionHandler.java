package todo1.exceptions;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	//when OutOfStock => response status 406
	@ExceptionHandler(StockOutException.class)
	public void sinStock(HttpServletResponse response) throws IOException{
		response.sendError(HttpStatus.NOT_ACCEPTABLE.value());
	}
	//when NotFound => response status 404
	@ExceptionHandler(ProductNotFoundException.class)
	public void noExisteProducto(HttpServletResponse response) throws IOException{
		response.sendError(HttpStatus.NOT_FOUND.value());
	}
	//when NotFound => response status 404
	@ExceptionHandler(StockNotFoundException.class)
	public void noExisteStock(HttpServletResponse response) throws IOException{
		response.sendError(HttpStatus.NOT_FOUND.value());
	}
}
