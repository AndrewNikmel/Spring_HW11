package Spring_HW.sem11.payment_serv.exception;

public class ExcessAmountException extends RuntimeException {
    public ExcessAmountException(String message) {
        super(message);
    }
}
