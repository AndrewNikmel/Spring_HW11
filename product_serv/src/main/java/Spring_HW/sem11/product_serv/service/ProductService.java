package Spring_HW.sem11.product_serv.service;

import Spring_HW.sem11.product_serv.entity.Product;
import Spring_HW.sem11.product_serv.exception.ExcessAmountException;
import Spring_HW.sem11.product_serv.exception.QuantityLessThanZeroException;
import Spring_HW.sem11.product_serv.exception.ResourceNotFoundException;
import Spring_HW.sem11.product_serv.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id) throws RuntimeException {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Товар " + id + " не найден!"));
    }

    @Transactional
    public void bayProduct(Long id, int amount) throws RuntimeException {
        if (amount <= 0) throw new QuantityLessThanZeroException("Количество должно быть больше 0!");
        Product product = getProductById(id);
        if (amount > product.getAmount())
            throw new ExcessAmountException("Заказ превышает остаток на складе!");
        product.setAmount(product.getAmount() - amount);
        product.setReserved(product.getReserved() - amount);
        productRepository.save(product);
    }

    @Transactional
    public void reservedProduct(Long id, int amount) throws RuntimeException {
        if (amount <= 0) throw new QuantityLessThanZeroException("Количество должно быть больше 0!");
        Product product = getProductById(id);
        if (amount > product.getAmount())
            throw new ExcessAmountException("Заказ превышает остаток на складе!");
        product.setReserved(amount);
        productRepository.save(product);
    }

    @Transactional
    public void reservedProductRollback(Long id, int amount){
        if (amount <= 0) throw new QuantityLessThanZeroException("Количество должно быть больше 0!");
        Product product = getProductById(id);
        product.setReserved(product.getReserved() - amount);
        productRepository.save(product);
    }
}
