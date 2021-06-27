package iam.phomenko.payment_portal.dto.paymentcreate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentCreateRequest {

    private Long source_acc_id;
    private Long dest_acc_id;
    private BigDecimal amount;
    private String reason;

}
