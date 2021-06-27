package iam.phomenko.payment_portal.dto.paymentgetlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentListRequest {
    private Long payer_id;
    private Long recipient_id;
    private Long source_acc_id;
    private Long dest_acc_id;

}
