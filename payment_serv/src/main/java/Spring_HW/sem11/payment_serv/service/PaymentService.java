package Spring_HW.sem11.payment_serv.service;

import Spring_HW.sem11.payment_serv.entity.Account;
import Spring_HW.sem11.payment_serv.entity.Payment;
import Spring_HW.sem11.payment_serv.exception.ExcessAmountException;
import Spring_HW.sem11.payment_serv.exception.ResourceNotFoundException;
import Spring_HW.sem11.payment_serv.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) throws RuntimeException {
        return accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Счет " + id + " не найден!"));
    }

    @Transactional
    public void payment(Payment payment) {
        Account creditAccount = getAccountById(payment.getCreditNumber());
        if((creditAccount.getBalance().compareTo(payment.getSum())) < 0) {
            throw new ExcessAmountException("Не достаточно средств!");
        }

        Account debitAccount = getAccountById(payment.getDebitNumber());

        creditAccount.setBalance(
                creditAccount.getBalance().subtract(payment.getSum()));
        debitAccount.setBalance(
                debitAccount.getBalance().add(payment.getSum()));

        accountRepository.save(creditAccount);
        accountRepository.save(debitAccount);
    }

    @Transactional
    public void paymentRollback(Payment payment) {
        Account debitAccount = getAccountById(payment.getDebitNumber());
        Account creditAccount = getAccountById(payment.getCreditNumber());

        debitAccount.setBalance(
                debitAccount.getBalance().subtract(payment.getSum()));
        creditAccount.setBalance(
                creditAccount.getBalance().add(payment.getSum()));

        accountRepository.save(creditAccount);
        accountRepository.save(debitAccount);
    }
}
