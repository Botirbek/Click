package com.example.psb_click.services;

import com.example.psb_click.dto.basic.DataDTO;
import com.example.psb_click.dto.request.*;
import com.example.psb_click.dto.response.*;
import com.example.psb_click.exceptions.CustomException;
import com.example.psb_click.util.Base;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClickService {

    @Value("${variable.jsonrpc}")
    private String JSONRPC;
    @Value("${variable.requestTimeOut}")
    private Long REQUEST_TIME_OUT;
    private final Base base;
    private final WebClient webClient;

    public ResponseEntity<DataDTO<CreateTokenResDTO>> createToken(CreateTokenReqDTO createTokenReqDTO) {
        CreateTokenResDTO createTokenResDTO = sendRequest(createTokenReqDTO, new ParameterizedTypeReference<>() {}, "token.create", 123);
        return ResponseEntity.ok(new DataDTO(createTokenResDTO));
    }

    public ResponseEntity<DataDTO<VerifyTokenResDTO>> verifyToken(VerifyTokenReqDTO verifyTokenReqDTO) {
        VerifyTokenResDTO verifyTokenResDTO = sendRequest(verifyTokenReqDTO,new ParameterizedTypeReference<>() {}, "token.verify", 124);
        return ResponseEntity.ok(new DataDTO(verifyTokenResDTO));
    }

    public ResponseEntity<DataDTO<String>> deleteToken(DeleteTokenReqDTO deleteTokenReqDTO) {
        String res = sendRequest(deleteTokenReqDTO, new ParameterizedTypeReference<>() {}, "token.remove", 123);
        return ResponseEntity.ok(new DataDTO(res));
    }

    public ResponseEntity<DataDTO<CategoryResDTO>> getCategories(){
        List<CategoryResDTO> categoryResDTOS = sendRequestList(null, new ParameterizedTypeReference<>() {}, "category.list", 126);
        return ResponseEntity.ok(new DataDTO(categoryResDTOS));
    }

    public ResponseEntity<DataDTO<CategoryIndoorResDTO>> getIndoorCategory(){
        List<CategoryIndoorResDTO> categoryIndoorResDTOS = sendRequestList(null, new ParameterizedTypeReference<>() {}, "indoor.category.list", 134567897);
        return ResponseEntity.ok(new DataDTO(categoryIndoorResDTOS));
    }

    public ResponseEntity<DataDTO<ServicesResDTO>> getServices(ServicesReqDTO servicesReqDTO) {
        List<ServicesResDTO> servicesResDTOS = sendRequestList(servicesReqDTO, new ParameterizedTypeReference<>(){}, "service.list", 203);
        return ResponseEntity.ok(new DataDTO(servicesResDTOS));
    }

    public ResponseEntity<DataDTO<ServiceIndoorResDTO>> getIndoorService(ServiceIndoorReqDTO serviceIndoorReqDTO) {
        List<ServicesResDTO> servicesResDTOS = sendRequestList(serviceIndoorReqDTO, new ParameterizedTypeReference<>(){}, "indoor.service.list", 203);
        return ResponseEntity.ok(new DataDTO(servicesResDTOS));
    }

    public ResponseEntity<DataDTO<FormResDTO>> getForm(FormReqDTO formReqDTO) {
        FormResDTO formResDTO = sendRequest(formReqDTO,new ParameterizedTypeReference<>() {}, "payment.data", 45230931);
        return ResponseEntity.ok(new DataDTO(formResDTO));
    }

    public ResponseEntity<DataDTO<Object>> getFormElement(FormElementReqDTO formElementReqDTO) {
        Object formElementResDTO = sendRequest(formElementReqDTO,new ParameterizedTypeReference<>() {}, "form.data", 300);
        return ResponseEntity.ok(new DataDTO(formElementResDTO));
    }

    public ResponseEntity<DataDTO<PaymentResDTO>> paymentToken(PaymentTokenReqDTO paymentTokenReqDTO){
        PaymentResDTO paymentResDTO = sendRequest(paymentTokenReqDTO, new ParameterizedTypeReference<>() {}, "payment.token", 130);
        return ResponseEntity.ok(new DataDTO<>(paymentResDTO));
    }

    public ResponseEntity<DataDTO<PaymentResDTO>> paymentCard(PaymentCardReqDTO paymentCardReqDTO){
        PaymentResDTO paymentResDTO = sendRequest(paymentCardReqDTO, new ParameterizedTypeReference<>() {}, "payment.token", 131);
        return ResponseEntity.ok(new DataDTO<>(paymentResDTO));
    }

    public ResponseEntity<DataDTO<CheckPaymentResDTO>> checkPayment(CheckPaymentReqDTO checkPaymentReqDTO){
        CheckPaymentResDTO checkPaymentResDTO = sendRequest(checkPaymentReqDTO, new ParameterizedTypeReference<BaseRes<CheckPaymentResDTO>>() {}, "payment.status", 140);
        return ResponseEntity.ok(new DataDTO<>(checkPaymentResDTO));
    }

    public ResponseEntity<DataDTO<CheckPaymentIdResDTO>> checkPaymentId(CheckPaymentIdReqDTO checkPaymentIdReqDTO){
        CheckPaymentIdResDTO checkPaymentIdResDTO = sendRequest(checkPaymentIdReqDTO, new ParameterizedTypeReference<BaseRes<CheckPaymentIdResDTO>>() {}, "payment.status.id", 141);
        return ResponseEntity.ok(new DataDTO<>(checkPaymentIdResDTO));
    }

    public ResponseEntity<DataDTO<PaymentResDTO>> transferToken(TransferTokenReqDTO transferTokenReqDTO){
        PaymentResDTO transferTokenResDTO = sendRequest(transferTokenReqDTO, new ParameterizedTypeReference<BaseRes<PaymentResDTO>>() {}, "transfer.token", 133);
        return ResponseEntity.ok(new DataDTO<>(transferTokenResDTO));
    }

    public ResponseEntity<DataDTO<PaymentResDTO>> transferCard(TransferCardReqDTO transferCardReqDTO){
        PaymentResDTO transferResDTO = sendRequest(transferCardReqDTO, new ParameterizedTypeReference<>() {}, "transfer.card", 134);
        return ResponseEntity.ok(new DataDTO<>(transferResDTO));
    }

    public ResponseEntity<DataDTO<PaymentResDTO>> transferToToken(TransferToTokenReqDTO transferCardReqDTO){
        PaymentResDTO transferResDTO = sendRequest(transferCardReqDTO, new ParameterizedTypeReference<>() {}, "transfer.card", 134);
        return ResponseEntity.ok(new DataDTO<>(transferResDTO));
    }
    private  <T,R> R sendRequest(
            T request,
            ParameterizedTypeReference<BaseRes<R>> responseType,
            String method,
            Integer id
    ){
        BaseRes<R> baseRes;
        var baseReq = BaseReq.builder()
                .id(id)
                .jsonrpc(JSONRPC)
                .method(method)
                .params(request)
                .build();

        try {
            baseRes = webClient.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Service", base.getServiceKey())
                    .bodyValue(baseReq)
                    .retrieve()
                    .bodyToMono(responseType)
                    .timeout(Duration.ofMillis(REQUEST_TIME_OUT))
                    .block();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if ((baseRes != null ? baseRes.getError() : null) != null)
            throw new CustomException(baseRes.getError().getMessage());

        return baseRes.getResult();
    }

    private  <T,R> List<R> sendRequestList(
            T request,
            ParameterizedTypeReference<BaseResList<R>> responseType,
            String method,
            Integer id
    ){
        BaseResList<R> baseRes;
        var baseReq = BaseReq.builder()
                .id(id)
                .jsonrpc(JSONRPC)
                .method(method)
                .params(request)
                .build();
        try {
            baseRes = webClient.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Service", base.getServiceKey())
                    .bodyValue(baseReq)
                    .retrieve()
                    .bodyToMono(responseType)
                    .timeout(Duration.ofMillis(REQUEST_TIME_OUT))
                    .block();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if ((baseRes != null ? baseRes.getError() : null) != null)
            throw new CustomException("Click: "+baseRes.getError().getMessage());

        return baseRes.getResult();
    }

}



