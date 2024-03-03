package Spring_HW.sem11.payment_serv.repository;

import Spring_HW.sem11.payment_serv.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}