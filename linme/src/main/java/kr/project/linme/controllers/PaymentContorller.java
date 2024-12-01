package kr.project.linme.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.project.linme.helpers.FileHelper;
import kr.project.linme.helpers.WebHelper;
import kr.project.linme.models.Payment;
import kr.project.linme.services.PaymentService;


@Controller
public class PaymentContorller {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private WebHelper webHelper;

    @Autowired
    private FileHelper fileHelper;

    @GetMapping("/payment/payment")
    public String payment(Model model,
        @RequestParam(value = "memberId", defaultValue = "1") int memberId
    ) {
        Payment payment = new Payment();

        List<Payment> output = null;

        try {
            output = paymentService.getList(payment);

            for ( Payment item : output) {
                item.setImg(fileHelper.getUrl(item.getImg()));
            }
        } catch (Exception e) {
            webHelper.serverError(e);
        }

        model.addAttribute("payment", output);

        return "payment/payment";
    }
}
