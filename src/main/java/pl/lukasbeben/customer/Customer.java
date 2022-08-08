package pl.lukasbeben.customer;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import pl.lukasbeben.bucklet.Bucklet;
import pl.lukasbeben.trainer.Trainer;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;


@Entity
@Table(name = "customers")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int clientId;

    @Size(max = 50)
    @NotBlank
    private String name;

    @Size (max = 50)
    @NotBlank
    private String surname;

    @Column(name = "cart_number", unique = true)
    @NotBlank
    private long cartNumber;

    @Column(name = "cart_deposit")
    @NotNull
    private boolean cartDeposit;

    @Email
    private String email;

    @Column(name = "phone_number")
    @Size (min=9, max = 9)
    private String phoneNumber;

    @Column (name = "purchase_date")
    @NotBlank
    private Date purchaseDate; // data zakupu karnetu

    @Size(max = 255)
    private String comment;

    @Column(name = "last_visit")
    private Date lastVisit;

    @ManyToOne
    @JoinColumn (name = "t_id")
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "b_id")

    private Bucklet bucklet;

}
