package pl.lukasbeben.customer;

import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.lukasbeben.bucklet.Bucklet;
import pl.lukasbeben.bucklet.BuckletService;
import pl.lukasbeben.trainer.Trainer;
import pl.lukasbeben.trainer.TrainerService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.sql.Date;
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

    @PostMapping(value = "/all")
    public String showCustomerBySurname(HttpServletRequest request, Model model){
        int size = customerService.customerBySurname(request.getParameter("surname")).size();
        model.addAttribute("customers", customerService.customerBySurname(request.getParameter("surname")));
        model.addAttribute("bucklets", buckletService.showAll());
        model.addAttribute("size", size);
        return "customers/all";
    }

    @GetMapping("/lastVisit")
    public String showLastVisit(Model model){
        int size = customerService.visitCustomer().size();
        model.addAttribute("size", size);
        model.addAttribute("bucklets", buckletService.showAll());
        model.addAttribute("customers", customerService.visitCustomer());
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
        return trainerService.findAll();
    }

    @ModelAttribute("bucklets")
    public List<Bucklet> setBucklet(){
        return buckletService.showAll();
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
        buckletService.setVisitsAndExpiryDate(customer);
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
        buckletService.setVisitsAndExpiryDate(customer);
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

    @GetMapping("/presence")
    public String notePresence(HttpServletRequest request, HttpServletResponse response, Model model){
        List <Cookie> cookiesList = List.of(request.getCookies());
        String info = null;
        for(Cookie c : cookiesList){
            if(c.getName().equals("info")){
                info = c.getValue();
                c.setMaxAge(0);
                response.addCookie(c);
            }
        }
        if(info != null){
        model.addAttribute("info", info);
        }
        return "customers/presence";
    }

    @PostMapping("/presence")
    public String notePresence(@RequestParam String cartNumber, HttpServletResponse response) {
        try {
            customerService.notePresence(cartNumber);
            Cookie c = new Cookie("info", "1");
            c.setPath("http://localhost:8080/customer/presence");
            response.addCookie(c);
        } catch (NullPointerException e){
            Cookie c = new Cookie("info", "0");
            c.setPath("http://localhost:8080/customer/presence");
            response.addCookie(c);
        }
        return "redirect: /customer/presence";
    }

    @GetMapping("/delete/{clientId}")
    public String deleteCustomer(@PathVariable int clientId){
        Customer customer = customerService.findById(clientId);
        customerService.deleteCustomer(customer);
        return "redirect:/customer/all";
    }

}
