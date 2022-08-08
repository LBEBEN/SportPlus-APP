package pl.lukasbeben.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/all")
    public String showAllCustomers(Model model){
        int size = customerService.showALL().size();
        model.addAttribute("customers", customerService.showALL());
        model.addAttribute("size", size);
        return "customers/all";
    }

    @GetMapping("/add")
    public String addCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "customers/add";
    }
}
