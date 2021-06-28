package iam.phomenko.payment_portal.controller;

import iam.phomenko.payment_portal.dto.*;
import iam.phomenko.payment_portal.dto.paymentcreate.PaymentCreateRequest;
import iam.phomenko.payment_portal.dto.paymentcreate.PaymentCreateResponse;
import iam.phomenko.payment_portal.dto.paymentgetlist.PaymentListRequest;
import iam.phomenko.payment_portal.entity.Payment;
import iam.phomenko.payment_portal.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<Payment> getAll() {
        return paymentService.getAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getAll(@PathVariable("id") String id) {
        if (ControllersUtils.validID(id))
            return Response.validationError(String.format("Invalid id %s, try correct id", id));
        Payment payment = paymentService.getOneById(Long.valueOf(id));
        if (payment == null)
            return Response.error(String.format("Payment with id %s doesn't exist", id));
        return Response.ok(payment);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody PaymentCreateRequest paymentCreateRequest) {
        if (!paymentService.validateRequest(paymentCreateRequest))
            return Response.validationError("Sum must be positive");
        Payment payment = paymentService.requestToPayment(paymentCreateRequest);
        if (paymentService.valid(payment))
            paymentService.pay(payment);
        else
            paymentService.save(payment);
        return Response.ok(new PaymentCreateResponse(payment.getId()), 201);
    }

    @PostMapping("/createList")
    public ResponseEntity<Object> createList(@RequestBody List<PaymentCreateRequest> paymentCreateRequests) {

        return Response.ok(paymentService.saveList(paymentCreateRequests));
    }

    @GetMapping(value = "/getList")
    public ResponseEntity<Object> getList(@RequestBody PaymentListRequest paymentListRequest) {

        return Response.ok(paymentService.getList(paymentListRequest));
    }
}