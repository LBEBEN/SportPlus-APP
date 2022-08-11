package pl.lukasbeben.customer;

import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.lukasbeben.bucklet.Bucklet;
import pl.lukasbeben.bucklet.BuckletService;
import pl.lukasbeben.trainer.Trainer;
import pl.lukasbeben.trainer.TrainerService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final TrainerService trainerService;
    private final BuckletService buckletService;

    @GetMapping("/all")
    public String showAllCustomers(Model model){
        int size = customerService.showALL().size();
        model.addAttribute("customers", customerService.showALL());
        model.addAttribute("bucklets", buckletService.showAll());
        model.addAttribute("size", size);
        return "customers/all";
    }

    //szczegóły klienta
    @GetMapping("/details/{clientId}")
    public String aboutTheCustomer(@PathVariable int clientId, Model model){
        model.addAttribute("customer", customerService.findById(clientId));
        return "/customers/details";
    }

    @ModelAttribute("trainers")
    public List<Trainer> setTrainer(){
        List<Trainer> trainers = trainerService.findAll();
        return trainers;
    }

    @ModelAttribute("bucklets")
    public List<Bucklet> setBucklet(){
        List<Bucklet> bucklets = buckletService.showAll();
        return  bucklets;
    }

    @GetMapping("/add")
    public String addCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "customers/add";
    }

    @PostMapping("/add")
    public String addCustomer(@Valid Customer customer, BindingResult result){
        if(result.hasErrors()){
            return "customers/add";
        }
        List<Bucklet> bucklets = buckletService.showAll();
        for (Bucklet b : bucklets){
            if (b.getBuckletId()== customer.getBucklet().getBuckletId()){
                customer.setVisitsLeft(b.getNumberOfVisits());
                customer.setExpiryDate(customer.getPurchaseDate().plusDays(customer.getBucklet().getValidity()));
            }
        }
        customerService.addCustomer(customer);
        return "redirect:/customer/all";
    }

    @GetMapping("/edit/{clientId}")
    public String editCustomer (@PathVariable int clientId, Model model){
        model.addAttribute("customer", customerService.findById(clientId));
        return "customers/add";
    }

    @PostMapping("/edit/{clientId}")
    public String editClient(Customer customer) {
        List<Bucklet> bucklets = buckletService.showAll();
        for (Bucklet b : bucklets) {
            if (b.getBuckletId() == customer.getBucklet().getBuckletId()) {
                customer.setVisitsLeft(b.getNumberOfVisits());
                customer.setExpiryDate(customer.getPurchaseDate().plusDays(customer.getBucklet().getValidity()));
            }}
            customerService.editCustomer(customer);
            return "redirect:/customer/all";
    }

//     odnawianie karnetu
    @GetMapping ("/renew/{clientId}")
    public String renewBucklet(@PathVariable int clientId, Model model){
        Customer customer = customerService.findById(clientId);
        List<Bucklet> bucklets = buckletService.showAll();
        model.addAttribute("name", customer.getName());
        model.addAttribute("surname", customer.getSurname());
        model.addAttribute("bucklets", bucklets);
        model.addAttribute("clientId", clientId);
        return "customers/renew";
    }

    @PostMapping("/renew/{clientId}")
    public String renewBucklet(@PathVariable int clientId, int buckletType, String date){
        if(date.isEmpty()){
            return "customers/renew";
        }
        Customer customer = customerService.findById(clientId);
        List<Bucklet> bucklet = buckletService.showAll();
        customer.setPurchaseDate(LocalDate.parse(date));
        for (Bucklet b: bucklet){
            if(b.getBuckletId() == buckletType){
                customer.setBucklet(b);
                customer.setVisitsLeft(b.getNumberOfVisits());
                customer.setExpiryDate(LocalDate.parse(date).plusDays(customer.getBucklet().getValidity()));
            }
        }
        customerService.editCustomer(customer);
        return "redirect:/customer/all";
    }

    @GetMapping("/delete/{clientId}")
    public String deleteCustomer(@PathVariable int clientId){
        Customer customer = customerService.findById(clientId);
        customerService.deleteCustomer(customer);
        return "redirect:/customer/all";
    }
}
