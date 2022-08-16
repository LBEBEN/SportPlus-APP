package pl.lukasbeben.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository <Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.cartNumber = ?1")
    Customer findByCartNumber(String cartNumber);
}
