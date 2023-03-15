package com.example.psb_click.services;

import com.example.psb_click.dto.basic.DataDTO;
import com.example.psb_click.dto.request.*;
import com.example.psb_click.dto.response.*;
import com.example.psb_click.entity.Logs;
import com.example.psb_click.enums.LogType;
import com.example.psb_click.exceptions.CustomException;
import com.example.psb_click.repository.LogsRepository;
import com.example.psb_click.util.Base;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClickService {

    @Value("${variable.jsonrpc}")
    private String JSONRPC;
    @Value("${variable.requestTimeOut}")
    private Long REQUEST_TIME_OUT;
    private final Base base;
    private final WebClient webClient;

    private final LogsRepository logsRepository;
    private final ObjectMapper objectMapper;

    Logger logger = LoggerFactory.getLogger("LoggingServiceImpl");

    public ResponseEntity<DataDTO<CreateTokenResDTO>> createToken(CreateTokenReqDTO createTokenReqDTO, String traceId) {
        CreateTokenResDTO createTokenResDTO = sendRequest(createTokenReqDTO, new ParameterizedTypeReference<>() {}, "token.create", 123,traceId);
        return ResponseEntity.ok(new DataDTO(createTokenResDTO));
    }

    public ResponseEntity<DataDTO<VerifyTokenResDTO>> verifyToken(VerifyTokenReqDTO verifyTokenReqDTO, String traceId) {
        VerifyTokenResDTO verifyTokenResDTO = sendRequest(verifyTokenReqDTO,new ParameterizedTypeReference<>() {}, "token.verify", 124,traceId);
        return ResponseEntity.ok(new DataDTO(verifyTokenResDTO));
    }

    public ResponseEntity<DataDTO<String>> deleteToken(DeleteTokenReqDTO deleteTokenReqDTO, String traceId) {
        String res = sendRequest(deleteTokenReqDTO, new ParameterizedTypeReference<>() {}, "token.remove", 123,traceId);
        return ResponseEntity.ok(new DataDTO(res));
    }

    public ResponseEntity<DataDTO<CategoryResDTO>> getCategories(String traceId){
        List<CategoryResDTO> categoryResDTOS = sendRequestList(null, new ParameterizedTypeReference<>() {}, "category.list", 126,traceId);
        return ResponseEntity.ok(new DataDTO(categoryResDTOS));
    }

    public ResponseEntity<DataDTO<CategoryIndoorResDTO>> getIndoorCategory(String traceId){
        List<CategoryIndoorResDTO> categoryIndoorResDTOS = sendRequestList(null, new ParameterizedTypeReference<>() {}, "indoor.category.list", 134567897,traceId);
        return ResponseEntity.ok(new DataDTO(categoryIndoorResDTOS));
    }

    public ResponseEntity<DataDTO<ServicesResDTO>> getServices(ServicesReqDTO servicesReqDTO, String traceId) {
        List<ServicesResDTO> servicesResDTOS = sendRequestList(servicesReqDTO, new ParameterizedTypeReference<>(){}, "service.list", 127,traceId);
        return ResponseEntity.ok(new DataDTO(servicesResDTOS));
    }

    public ResponseEntity<DataDTO<ServiceIndoorResDTO>> getIndoorService(ServiceIndoorReqDTO serviceIndoorReqDTO, String traceId) {
        List<ServicesResDTO> servicesResDTOS = sendRequestList(serviceIndoorReqDTO, new ParameterizedTypeReference<>(){}, "indoor.service.list", 203,traceId);
        return ResponseEntity.ok(new DataDTO(servicesResDTOS));
    }

    public ResponseEntity<DataDTO<FormResDTO>> getForm(FormReqDTO formReqDTO, String traceId) {
        FormResDTO formResDTO = sendRequest(formReqDTO,new ParameterizedTypeReference<>() {}, "payment.data", 45230931,traceId);
        return ResponseEntity.ok(new DataDTO(formResDTO));
    }

    public ResponseEntity<DataDTO<Object>> getFormElement(FormElementReqDTO formElementReqDTO, String traceId) {
        Object formElementResDTO = sendRequest(formElementReqDTO,new ParameterizedTypeReference<>() {}, "form.data", 300,traceId);
        return ResponseEntity.ok(new DataDTO(formElementResDTO));
    }

    public ResponseEntity<DataDTO<PaymentResDTO>> paymentToken(PaymentTokenReqDTO paymentTokenReqDTO, String traceId){
        PaymentResDTO paymentResDTO = sendRequest(paymentTokenReqDTO, new ParameterizedTypeReference<>() {}, "payment.token", 130,traceId);
        return ResponseEntity.ok(new DataDTO<>(paymentResDTO));
    }

    public ResponseEntity<DataDTO<PaymentResDTO>> paymentCard(PaymentCardReqDTO paymentCardReqDTO, String traceId){
        PaymentResDTO paymentResDTO = sendRequest(paymentCardReqDTO, new ParameterizedTypeReference<>() {}, "payment.token", 131,traceId);
        return ResponseEntity.ok(new DataDTO<>(paymentResDTO));
    }

    public CheckPaymentResDTO checkPayment(CheckPaymentReqDTO checkPaymentReqDTO, String traceId){
        return sendRequest(checkPaymentReqDTO, new ParameterizedTypeReference<BaseRes<CheckPaymentResDTO>>() {}, "payment.status", 140, traceId);
    }

    public ResponseEntity<DataDTO<CheckPaymentIdResDTO>> checkPaymentId(CheckPaymentIdReqDTO checkPaymentIdReqDTO, String traceId){
        CheckPaymentIdResDTO checkPaymentIdResDTO = sendRequest(checkPaymentIdReqDTO, new ParameterizedTypeReference<BaseRes<CheckPaymentIdResDTO>>() {}, "payment.status.id", 141,traceId);
        return ResponseEntity.ok(new DataDTO<>(checkPaymentIdResDTO));
    }

    public ResponseEntity<DataDTO<PaymentResDTO>> transferToken(TransferTokenReqDTO transferTokenReqDTO, String traceId){
        PaymentResDTO response = sendRequest(transferTokenReqDTO, new ParameterizedTypeReference<BaseRes<PaymentResDTO>>() {}, "transfer.token", 133,traceId);
        return ResponseEntity.ok(new DataDTO<>(response));
    }

    public ResponseEntity<DataDTO<PaymentResDTO>> transferCard(TransferCardReqDTO transferCardReqDTO, String traceId){
        PaymentResDTO response = sendRequest(transferCardReqDTO, new ParameterizedTypeReference<>() {}, "transfer.card", 134,traceId);
        return ResponseEntity.ok(new DataDTO<>(response));
    }

    public ResponseEntity<DataDTO<PaymentResDTO>> transferToToken(TransferToTokenReqDTO transferCardReqDTO, String traceId){
        PaymentResDTO response = sendRequest(transferCardReqDTO, new ParameterizedTypeReference<>() {}, "transfer.token2token", 135,traceId);
        return ResponseEntity.ok(new DataDTO<>(response));
    }

    public ResponseEntity<DataDTO<PaymentResDTO>> transferData(TransferDataReqDTO transferDataReqDTO, String traceId){
        PaymentResDTO transferResDTO = sendRequest(transferDataReqDTO, new ParameterizedTypeReference<>() {}, "transfer.data", 141,traceId);
        return ResponseEntity.ok(new DataDTO<>(transferResDTO));
    }

    public   <T,R> R sendRequest(
            T request,
            ParameterizedTypeReference<BaseRes<R>> responseType,
            String method,
            Integer id,
            String trace_id
    ){
        BaseRes<R> baseRes;
        UUID logId = UUID.randomUUID();

        var baseReq = BaseReq.builder()
                .id(id)
                .jsonrpc(JSONRPC)
                .method(method)
                .params(request)
                .build();

        try {
            logsRepository.save(
                    Logs.builder()
                            .body(objectMapper.writeValueAsString(baseReq))
                            .traceId(trace_id)
                            .type(LogType.ClickRequest)
                            .logId(logId)
                            .time(LocalDateTime.now())
                            .method(method)
                            .build());
//            logger.info("log ClickRequest: {}", baseReq);

            baseRes = webClient.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Service", base.getServiceKey())
                    .bodyValue(baseReq)
                    .retrieve()
                    .bodyToMono(responseType)
                    .timeout(Duration.ofMillis(REQUEST_TIME_OUT))
                    .block();

            logsRepository.save(
                    Logs.builder()
                            .body(objectMapper.writeValueAsString(baseRes))
                            .traceId(trace_id)
                            .type(LogType.ClickResponse)
                            .logId(logId)
                            .time(LocalDateTime.now())
                            .method(method)
                            .build());
//            logger.info("log ClickResponse: {}", baseRes);
        } catch (JsonProcessingException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        if ((baseRes != null ? baseRes.getError() : null) != null)
            throw new CustomException(
                    JErrorRes.builder()
                            .message("Click: " + baseRes.getError().getMessage())
                            .traceId(trace_id)
                            .build());

        return baseRes.getResult();
    }

    public   <T,R> List<R> sendRequestList(
            T request,
            ParameterizedTypeReference<BaseResList<R>> responseType,
            String method,
            Integer id,
            String trace_id
    ){
        BaseResList<R> baseRes;
        var baseReq = BaseReq.builder()
                .id(id)
                .jsonrpc(JSONRPC)
                .method(method)
                .params(request)
                .build();

        UUID logId = UUID.randomUUID();

        try {
            logsRepository.save(
                    Logs.builder()
                            .body(objectMapper.writeValueAsString(baseReq))
                            .traceId(trace_id)
                            .type(LogType.ClickRequest)
                            .logId(logId)
                            .time(LocalDateTime.now())
                            .method(method)
                            .build());
//            logger.info("log ClickRequest: {}", baseReq);

            baseRes = webClient.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Service", base.getServiceKey())
                    .bodyValue(baseReq)
                    .retrieve()
                    .bodyToMono(responseType)
                    .timeout(Duration.ofMillis(REQUEST_TIME_OUT))
                    .block();

            logsRepository.save(
                    Logs.builder()
                            .body(objectMapper.writeValueAsString(baseRes))
                            .traceId(trace_id)
                            .type(LogType.ClickResponse)
                            .logId(logId)
                            .time(LocalDateTime.now())
                            .method(method)
                            .build());
//            logger.info("log ClickResponse: {}", baseRes);
        }catch (NoSuchAlgorithmException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if ((baseRes != null ? baseRes.getError() : null) != null)
            throw new CustomException(
                    JErrorRes.builder()
                            .message("Click: " + baseRes.getError().getMessage())
                            .traceId(trace_id)
                            .build());

        return baseRes.getResult();
    }

}



