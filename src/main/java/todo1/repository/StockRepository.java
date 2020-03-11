package todo1.repository;

import todo1.model.Brand;
import todo1.model.Product;
import todo1.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findByProduct(Product product);

}
