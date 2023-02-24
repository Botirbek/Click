package com.example.psb_click.controller;

import com.example.psb_click.dto.basic.DataDTO;
import com.example.psb_click.dto.request.*;
import com.example.psb_click.dto.response.*;
import com.example.psb_click.services.ClickService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/click")
public class ClickController {

    private final ClickService clickService;

    @PostMapping("/token/create")
    public ResponseEntity<DataDTO<CreateTokenResDTO>> createToken(@RequestBody CreateTokenReqDTO createTokenReqDTO){
        return clickService.createToken(createTokenReqDTO);
    }

    @PostMapping("/token/verify")
    public ResponseEntity<DataDTO<VerifyTokenResDTO>> verifyToken(@RequestBody VerifyTokenReqDTO verifyTokenReqDTO){
        return clickService.verifyToken(verifyTokenReqDTO);
    }

    @PostMapping("/token/delete")
    public ResponseEntity<DataDTO<String>> deleteToken(@RequestBody DeleteTokenReqDTO deleteTokenReqDTO){
        return clickService.deleteToken(deleteTokenReqDTO);
    }

    @GetMapping("/category/get")
    public ResponseEntity<DataDTO<CategoryResDTO>> getCategories(){
        return clickService.getCategories();
    }

    @GetMapping("/category/getIndoor")
    public ResponseEntity<DataDTO<CategoryIndoorResDTO>> getIndoorCategory(){
        return clickService.getIndoorCategory();
    }

    @PostMapping("/service/get")
    public ResponseEntity<DataDTO<ServicesResDTO>> getServices(@RequestBody ServicesReqDTO servicesReqDTO){
        return clickService.getServices(servicesReqDTO);
    }

    @PostMapping("/service/getIndoor")
    public ResponseEntity<DataDTO<ServiceIndoorResDTO>> getIndoorService(@RequestBody ServiceIndoorReqDTO serviceIndoorReqDTO){
        return clickService.getIndoorService(serviceIndoorReqDTO);
    }

    @PostMapping("/form/get")
    public ResponseEntity<DataDTO<FormResDTO>> getForm(@RequestBody FormReqDTO formReqDTO){
        return clickService.getForm(formReqDTO);
    }

    @PostMapping("/form/getElement")
    public ResponseEntity<DataDTO<Object>> getFormElement(@RequestBody FormElementReqDTO formElementReqDTO){
        return clickService.getFormElement(formElementReqDTO);
    }

    @PostMapping("/payment/token")
    public ResponseEntity<DataDTO<PaymentResDTO>> paymentToken(@RequestBody PaymentTokenReqDTO paymentTokenReqDTO){
        return clickService.paymentToken(paymentTokenReqDTO);
    }

    @PostMapping("/payment/card")
    public ResponseEntity<DataDTO<PaymentResDTO>> paymentCard(@RequestBody PaymentCardReqDTO paymentCardReqDTO){
        return clickService.paymentCard(paymentCardReqDTO);
    }

    @PostMapping("/payment/check")
    public ResponseEntity<DataDTO<CheckPaymentResDTO>> checkPayment(@RequestBody  CheckPaymentReqDTO checkPaymentReqDTO){
        return clickService.checkPayment(checkPaymentReqDTO);
    }

    @PostMapping("/payment/checkId")
    public ResponseEntity<DataDTO<CheckPaymentIdResDTO>> checkPaymentId(@RequestBody  CheckPaymentIdReqDTO checkPaymentIdReqDTO){
        return clickService.checkPaymentId(checkPaymentIdReqDTO);
    }

    @PostMapping("/transfer/token")
    public ResponseEntity<DataDTO<PaymentResDTO>> transferToken(@RequestBody TransferTokenReqDTO transferTokenReqDTO){
        return clickService.transferToken(transferTokenReqDTO);
    }

    @PostMapping("/transfer/card")
    public ResponseEntity<DataDTO<PaymentResDTO>> transferCard(@RequestBody TransferCardReqDTO transferCardReqDTO){
        return clickService.transferCard(transferCardReqDTO);
    }

    @PostMapping("/transfer/tokenToToken")
    public ResponseEntity<DataDTO<PaymentResDTO>> transferTokenToToken(@RequestBody TransferToTokenReqDTO transferToTokenReqDTO){
        return clickService.transferToToken(transferToTokenReqDTO);
    }

}
