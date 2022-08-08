package pl.lukasbeben.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.lukasbeben.trainer.Trainer;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerDao customerDao;

    public List<Customer> showALL(){
        return customerDao.showAll();
    }

    public Customer findById (int id){
        return customerDao.findById(id);
    }

    public void addCustomer(Customer customer){customerDao.addCustomer(customer);}

    public void editCustomer(Customer customer){
        customerDao.edit(customer);
    }

    public void deleteCustomer(Customer customer){
        customerDao.delete(customer);
    }

}
