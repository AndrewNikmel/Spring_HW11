package Spring_HW.sem11.payment_serv.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Payment {
    private Long creditNumber;
    private Long debitNumber;
    private BigDecimal sum;

}
