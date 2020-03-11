package todo1.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrandNotFoundException extends RuntimeException {

	private static final Logger log = LoggerFactory.getLogger(BrandNotFoundException.class);

	public BrandNotFoundException(Long id) {
		super("La marca con el identificador: "+id +" no existe.");
		log.error("La marca con el identificador: "+id +" no existe.");
	}
}
