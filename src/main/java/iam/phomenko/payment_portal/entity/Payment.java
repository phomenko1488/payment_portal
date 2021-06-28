package iam.phomenko.payment_portal.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account destinationAccount;
    @ManyToOne
    private Account sourceAccount;

    private BigDecimal amount;

    private String reason;

    private Calendar timestamp;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

}
