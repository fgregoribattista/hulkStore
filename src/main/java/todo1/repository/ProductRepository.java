package todo1.repository;

import todo1.model.Product;
import todo1.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByDescriptionAndBrand(String description, Brand brand);

}
