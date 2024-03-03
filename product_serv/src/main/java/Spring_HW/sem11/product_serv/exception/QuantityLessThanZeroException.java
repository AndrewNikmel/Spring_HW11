package Spring_HW.sem11.product_serv.exception;

public class QuantityLessThanZeroException extends RuntimeException {
    public QuantityLessThanZeroException(String message) {
        super(message);
    }
}
