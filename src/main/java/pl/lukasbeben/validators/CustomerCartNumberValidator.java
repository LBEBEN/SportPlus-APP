package pl.lukasbeben.validators;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import pl.lukasbeben.customer.Customer;
import pl.lukasbeben.customer.CustomerService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

//@RequiredArgsConstructor
public class CustomerCartNumberValidator implements ConstraintValidator<CustomerCartNumber, String> {

    @Autowired
    private CustomerService customerService;

//    public CustomerCartNumberValidator(){}

    public CustomerCartNumberValidator (CustomerService customerService){
        this.customerService = customerService;
    }

    @Override
    public void initialize(CustomerCartNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String cartNumber, ConstraintValidatorContext context){
        boolean confirm = true;
        List<Customer> customerCartNumber = customerService.showALL();
        for(Customer c : customerCartNumber) {
            if(c.getCartNumber().equals(cartNumber))
            confirm = false;
        }
        return confirm;
    }
}
