package iam.phomenko.payment_portal.dto.paymentcreate;

import iam.phomenko.payment_portal.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SinglePaymentCreateResponse {
    private Long payment_id;
    private PaymentStatus status;
}
