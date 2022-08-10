package pl.lukasbeben.trainer;

import lombok.Data;
import pl.lukasbeben.customer.Customer;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "trainers")
@Data
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    private int trainerId;

    @Size(max = 50)
    @NotBlank (message = "Pole nie może być puste")
    private String name;

    @Size (max = 50)
    @NotBlank (message = "Pole nie może być puste")
    private String surname;

    private String descryption; // będzie wybierany z listy rozwijanej

    //metoda pozwalając wyświetlać imię i nazwisko trenera w widoku
    public String getDisplayName(){
        return name + " " + surname;
    }

}
