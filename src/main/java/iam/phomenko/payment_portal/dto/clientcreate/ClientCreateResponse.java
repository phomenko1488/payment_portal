package iam.phomenko.payment_portal.dto.clientcreate;

import iam.phomenko.payment_portal.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientCreateResponse {
    private Long client_id;

    public static ClientCreateResponse fromClient(Client client) {
        return new ClientCreateResponse(client.getId());
    }
}
