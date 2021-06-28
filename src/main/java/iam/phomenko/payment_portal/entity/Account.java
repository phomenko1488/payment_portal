package iam.phomenko.payment_portal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString

@Entity
@Table(name = "t_accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private Long number;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Client owner;
}
