package Spring_HW.sem11.product_serv.repository;

import Spring_HW.sem11.product_serv.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
