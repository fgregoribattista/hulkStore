package todo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import todo1.model.Brand;
import todo1.model.Product;
import todo1.model.Stock;
import todo1.repository.BrandRepository;
import todo1.repository.ProductRepository;
import todo1.repository.StockRepository;


@SpringBootApplication
public class KardexApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(KardexApplication.class);
	// static init data
	private static final String VASO = "VASO";
	private static final String CAMISETA = "CAMISETA";
	private static final String COMICS = "COMICS";
	private static final String JUGUETE = "JUGUETE";
	private static final String ACCESORIO = "ACCESORIO";
	private static final Long CANTIDAD = 100L;
	private static final Long SIN_CANTIDAD = 0L;
	
	
	@Autowired
    private ProductRepository productRepository;
	
	@Autowired
    private BrandRepository brandRepository;

	@Autowired
	private StockRepository stockRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(KardexApplication.class, args);
	}
	
	@Override
    public void run(String... args) {
        // Set init productos & stock
		insertProductsAndStock();
	}



	private void insertProductsAndStock() {

		log.info("Se inicia la carga de productos y stock inicial");

		//carga de marcas
		Brand brandDC = new Brand();
		brandDC.setId(1L);
		brandDC.setDescription("DC Comics");
		brandDC = brandRepository.save(brandDC);

		Brand brandMarvel = new Brand();
		brandMarvel.setId(2L);
		brandMarvel.setDescription("Marvel");
		brandMarvel = brandRepository.save(brandMarvel);

		Brand brandAlternativa = new Brand();
		brandAlternativa.setId(3L);
		brandAlternativa.setDescription("Alternativo");
		brandAlternativa = brandRepository.save(brandAlternativa);

		//carga de productos
		Product productVasoDC = new Product();
		productVasoDC.setCode(1L);
		productVasoDC.setBrand(brandDC);
		productVasoDC.setDescription(VASO);


		Product productCamisetaDC = new Product();
		productCamisetaDC.setCode(2L);
		productCamisetaDC.setBrand(brandDC);
		productCamisetaDC.setDescription(CAMISETA);


		Product productComicsDC = new Product();
		productComicsDC.setCode(3L);
		productComicsDC.setBrand(brandDC);
		productComicsDC.setDescription(COMICS);


		Product productJugueteDC = new Product();
		productJugueteDC.setCode(4L);
		productJugueteDC.setBrand(brandDC);
		productJugueteDC.setDescription(JUGUETE);


		Product productAccesorioDC = new Product();
		productAccesorioDC.setCode(5L);
		productAccesorioDC.setBrand(brandDC);
		productAccesorioDC.setDescription(ACCESORIO);


		Product productVasoMarvel = new Product();
		productVasoMarvel.setCode(6L);
		productVasoMarvel.setBrand(brandMarvel);
		productVasoMarvel.setDescription(VASO);


		Product productCamisetaMarvel = new Product();
		productCamisetaMarvel.setCode(7L);
		productCamisetaMarvel.setBrand(brandMarvel);
		productCamisetaMarvel.setDescription(CAMISETA);


		Product productComicsMarvel = new Product();
		productComicsMarvel.setCode(8L);
		productComicsMarvel.setBrand(brandMarvel);
		productComicsMarvel.setDescription(COMICS);


		Product productJugueteMarvel = new Product();
		productJugueteMarvel.setCode(9L);
		productJugueteMarvel.setBrand(brandMarvel);
		productJugueteMarvel.setDescription(JUGUETE);


		Product productAccesorioMarvel = new Product();
		productAccesorioMarvel.setCode(10L);
		productAccesorioMarvel.setBrand(brandMarvel);
		productAccesorioMarvel.setDescription(ACCESORIO);


		Product productVasoAlt = new Product();
		productVasoAlt.setCode(11L);
		productVasoAlt.setBrand(brandAlternativa);
		productVasoAlt.setDescription(VASO);


		Product productCamisetaAlt = new Product();
		productCamisetaAlt.setCode(12L);
		productCamisetaAlt.setBrand(brandAlternativa);
		productCamisetaAlt.setDescription(CAMISETA);


		Product productComicsAlt = new Product();
		productComicsAlt.setCode(13L);
		productComicsAlt.setBrand(brandAlternativa);
		productComicsAlt.setDescription(COMICS);


		Product productJugueteAlt = new Product();
		productJugueteAlt.setCode(14L);
		productJugueteAlt.setBrand(brandAlternativa);
		productJugueteAlt.setDescription(JUGUETE);


		Product productAccesorioAlt = new Product();
		productAccesorioAlt.setCode(15L);
		productAccesorioAlt.setBrand(brandAlternativa);
		productAccesorioAlt.setDescription(ACCESORIO);


		//carga de stock inicial
		Stock stockVasoDC = new Stock();
		stockVasoDC.setProduct(productVasoDC);
		stockVasoDC.setQuantity(CANTIDAD);
		stockRepository.save(stockVasoDC);

		Stock stockCamisetaDc = new Stock();
		stockCamisetaDc.setProduct(productCamisetaDC);
		stockCamisetaDc.setQuantity(CANTIDAD);
		stockRepository.save(stockCamisetaDc);

		Stock stockComicsDC = new Stock();
		stockComicsDC.setProduct(productComicsDC);
		stockComicsDC.setQuantity(CANTIDAD);
		stockRepository.save(stockComicsDC);

		Stock stockJugueteDc = new Stock();
		stockJugueteDc.setProduct(productJugueteDC);
		stockJugueteDc.setQuantity(CANTIDAD);
		stockRepository.save(stockJugueteDc);

		Stock stockAccesorioDC = new Stock();
		stockAccesorioDC.setProduct(productAccesorioDC);
		stockAccesorioDC.setQuantity(CANTIDAD);
		stockRepository.save(stockAccesorioDC);

		Stock stockVasoMarvel = new Stock();
		stockVasoMarvel.setProduct(productVasoMarvel);
		stockVasoMarvel.setQuantity(CANTIDAD);
		stockRepository.save(stockVasoMarvel);

		Stock stockCamisetaMarvel= new Stock();
		stockCamisetaMarvel.setProduct(productCamisetaMarvel);
		stockCamisetaMarvel.setQuantity(CANTIDAD);
		stockRepository.save(stockCamisetaMarvel);

		Stock stockComicsMarvel= new Stock();
		stockComicsMarvel.setProduct(productComicsMarvel);
		stockComicsMarvel.setQuantity(CANTIDAD);
		stockRepository.save(stockComicsMarvel);

		Stock stockJugueteMarvel = new Stock();
		stockJugueteMarvel.setProduct(productJugueteMarvel);
		stockJugueteMarvel.setQuantity(CANTIDAD);
		stockRepository.save(stockJugueteMarvel);

		Stock stockAccesorioMarvel = new Stock();
		stockAccesorioMarvel.setProduct(productAccesorioMarvel);
		stockAccesorioMarvel.setQuantity(CANTIDAD);
		stockRepository.save(stockAccesorioMarvel);

		Stock stockVasoAlt = new Stock();
		stockVasoAlt.setProduct(productVasoAlt);
		stockVasoAlt.setQuantity(CANTIDAD);
		stockRepository.save(stockVasoAlt);

		Stock stockCamisetaAlt= new Stock();
		stockCamisetaAlt.setProduct(productCamisetaAlt);
		stockCamisetaAlt.setQuantity(CANTIDAD);
		stockRepository.save(stockCamisetaAlt);

		Stock stockAccesorioAlt= new Stock();
		stockAccesorioAlt.setProduct(productAccesorioAlt);
		stockAccesorioAlt.setQuantity(CANTIDAD);
		stockRepository.save(stockAccesorioAlt);

		Stock stockComicsAlt= new Stock();
		stockComicsAlt.setProduct(productComicsAlt);
		stockComicsAlt.setQuantity(SIN_CANTIDAD);
		stockRepository.save(stockComicsAlt);

		Stock stockJugueteAlt = new Stock();
		stockJugueteAlt.setProduct(productJugueteAlt);
		stockJugueteAlt.setQuantity(SIN_CANTIDAD);
		stockRepository.save(stockJugueteAlt);


		log.info("Fin de carga inicial.");
	}

}
