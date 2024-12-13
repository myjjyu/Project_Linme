package kr.project.linme.controllers.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import kr.project.linme.helpers.RestHelper;
import kr.project.linme.models.Payment;
import kr.project.linme.services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class PaymentRestController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RestHelper restHelper;
}
