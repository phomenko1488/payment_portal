package iam.phomenko.payment_portal.service;

import iam.phomenko.payment_portal.dto.paymentcreate.PaymentCreateRequest;
import iam.phomenko.payment_portal.dto.paymentgetlist.PaymentListRequest;
import iam.phomenko.payment_portal.dto.paymentgetlist.PaymentListResponse;
import iam.phomenko.payment_portal.dto.paymentcreate.SinglePaymentCreateResponse;
import iam.phomenko.payment_portal.entity.Account;
import iam.phomenko.payment_portal.entity.Client;
import iam.phomenko.payment_portal.entity.Payment;
import iam.phomenko.payment_portal.entity.PaymentStatus;
import iam.phomenko.payment_portal.repository.AccountRepository;
import iam.phomenko.payment_portal.repository.ClientRepository;
import iam.phomenko.payment_portal.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, AccountRepository accountRepository, ClientRepository clientRepository) {
        this.paymentRepository = paymentRepository;
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    public Payment getOneById(Long id) {
        return Optional.of(paymentRepository.getPaymentById(id)).orElse(null);
    }


    public void save(Payment payment) {
        paymentRepository.save(payment);
    }

    private void sendMoney(Account destinationAccount, Account sourceAccount, BigDecimal amount) {
        destinationAccount.setBalance(destinationAccount.getBalance().add(amount));
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
    }

    public void pay(Payment payment) {
        Account sourceAccount = payment.getSourceAccount();
        Account destinationAccount = payment.getDestinationAccount();

        BigDecimal paymentAmount = payment.getAmount();
        if (sourceAccount.getBalance().doubleValue() < paymentAmount.doubleValue()) {
            payment.setStatus(PaymentStatus.ERROR);
            save(payment);
            return;
        }
        sendMoney(destinationAccount, sourceAccount, paymentAmount);
        payment.setStatus(PaymentStatus.OK);
        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);
        save(payment);
    }

    public Payment requestToPayment(PaymentCreateRequest request) {
        Payment payment = new Payment();
        payment.setAmount(request.getAmount());
        Account destinationAccount = accountRepository.getAccountById(request.getDest_acc_id());
        Account sourceAccount = accountRepository.getAccountById(request.getSource_acc_id());
        payment.setDestinationAccount(destinationAccount);
        payment.setSourceAccount(sourceAccount);
        payment.setReason(request.getReason());
        payment.setTimestamp(Calendar.getInstance());
        return payment;
    }

    public boolean valid(Payment payment) {
        if (payment.getDestinationAccount() == null || payment.getSourceAccount() == null || payment.getAmount().doubleValue() <= 0) {
            payment.setStatus(PaymentStatus.ERROR);
            return false;
        } else
            payment.setStatus(PaymentStatus.OK);
        return true;
    }

    public List<SinglePaymentCreateResponse> saveList(List<PaymentCreateRequest> requests) {
        List<Payment> payments = new ArrayList<>();

        List<SinglePaymentCreateResponse> responses = new ArrayList<>();
        requests.forEach(request -> {
                    Payment payment = requestToPayment(request);
                    if (valid(payment))
                        pay(payment);
                    else
                        save(payment);
                    payments.add(payment);
                }
        );
        payments.forEach(payment -> responses.add(new SinglePaymentCreateResponse(payment.getId(), payment.getStatus())));
        return responses;
    }

    public List<PaymentListResponse> getList(PaymentListRequest paymentListRequest) {
        Account sourceAccount = accountRepository.getAccountById(paymentListRequest.getSource_acc_id());
        if (sourceAccount == null)
            return new ArrayList<>();
        Account destinationAccount = accountRepository.getAccountById(paymentListRequest.getDest_acc_id());
        if (destinationAccount == null)
            return new ArrayList<>();
        Client payer = clientRepository.getClientById(paymentListRequest.getPayer_id());
        if (payer == null)
            return new ArrayList<>();
        Client recipient = clientRepository.getClientById(paymentListRequest.getRecipient_id());
        if (recipient == null)
            return new ArrayList<>();
        List<Payment> payments = paymentRepository.getPaymentsBySourceAccountAndDestinationAccount(sourceAccount, destinationAccount);
        List<PaymentListResponse> responses = new ArrayList<>();
        payments.forEach(payment -> {
            PaymentListResponse paymentListResponse = new PaymentListResponse(payment.getId(), Calendar.getInstance(), sourceAccount.getNumber(), destinationAccount.getNumber(), payment.getAmount(), payer, recipient);
            responses.add(paymentListResponse);
        });
        return responses;
    }

    public boolean validateRequest(PaymentCreateRequest paymentCreateRequest) {
        return paymentCreateRequest.getAmount().doubleValue() >= 0;
    }
}
