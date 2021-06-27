package iam.phomenko.payment_portal.dto.paymentgetlist;

import iam.phomenko.payment_portal.entity.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Calendar;

@NoArgsConstructor
@Data
public class PaymentListResponse {
    private Long payment_id;
    private Calendar timestamp;
    private Long src_acc_num;
    private Long dest_acc_num;
    private BigDecimal amount;

    private ClientDTO payer;
    private ClientDTO recipient;

    private Client toResponseClient(Client client) {
        client.setAccounts(null);
        client.setId(null);
        return client;
    }

    public PaymentListResponse(Long payment_id, Calendar timestamp, Long src_acc_num, Long dest_acc_num, BigDecimal amount, Client payer, Client recipient) {
        this.payment_id = payment_id;
        this.timestamp = timestamp;
        this.src_acc_num = src_acc_num;
        this.dest_acc_num = dest_acc_num;
        this.amount = amount;
        this.payer = new ClientDTO(payer);
        this.recipient = new ClientDTO(recipient);
    }
}
