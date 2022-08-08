package pl.lukasbeben.trainer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/trainer")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    @RequestMapping ("/all")
    public String showAllTrainers(Model model){
        int size = trainerService.findAll().size();
        model.addAttribute("trainers", trainerService.findAll());
        model.addAttribute("size", size);
        return "trainers/all";
    }

    //lista rozwijana do typów trenerów
    @ModelAttribute("descryptions")
    public List<String> setDescryption(){
        List <String> descryption = new ArrayList<>();
        descryption.add("Sport Plus");
        descryption.add("PERSONALNY");
        return descryption;
    }

    @GetMapping("/add")
    public String addTrainer(Model model){
        model.addAttribute("trainer", new Trainer());
        return "trainers/add";
    }

    @PostMapping("/add")
    public String addTrainer(@Valid Trainer trainer, BindingResult result){
        if(result.hasErrors()){
            return "trainers/add";
        }
        trainerService.addTrainer(trainer);
        return "redirect:/trainer/all";
    }

    @GetMapping("/edit/{trainerId}")
    public String editTrainer(@PathVariable int trainerId, Model model){
        model.addAttribute("trainer", trainerService.findById(trainerId));
        return "/trainers/add";
    }

    @PostMapping("/edit/{trainerId}")
    public String editTrainer(Trainer trainer){
        trainerService.editTrainer(trainer);
        return "redirect:/trainer/all";
    }

    @GetMapping("/delete/{trainerId}")
    public String deleteTrainer(@PathVariable int trainerId) {
        Trainer trainer = trainerService.findById(trainerId);
        trainerService.deleteTrainer(trainer);
        return "redirect:/trainer/all";
    }

}
