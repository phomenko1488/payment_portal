package iam.phomenko.payment_portal.dto.paymentgetlist;

import iam.phomenko.payment_portal.entity.Client;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClientDTO {
    public String first_name;
    public String last_name;

    ClientDTO(Client client){
        this.first_name=client.getFirstName();
        this.last_name=client.getLastName();
    }
}
