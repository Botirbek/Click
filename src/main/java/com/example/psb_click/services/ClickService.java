package com.example.psb_click.services;

import com.example.psb_click.dto.basic.DataDTO;
import com.example.psb_click.dto.request.BaseReq;
import com.example.psb_click.dto.request.PaymentReqDTO;
import com.example.psb_click.dto.response.BaseRes;
import com.example.psb_click.dto.response.BaseResList;
import com.example.psb_click.dto.response.CategoryResDTO;
import com.example.psb_click.dto.response.PaymentResDTO;
import com.example.psb_click.exceptions.CustomException;
import com.example.psb_click.util.Base;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClickService {

    @Value("${variable.baseUrl}")
    private final String baseUrl;
    private final Base base;
    private final WebClient webClient;

    public ResponseEntity<DataDTO<List<CategoryResDTO>>> getCategories(){

        BaseResList<CategoryResDTO> baseResList;
        BaseReq baseReq = BaseReq.builder()
                                .id(126)
                                .jsonrpc("2.0")
                                .method("category.list")
                                .build();
        try {
            baseResList =  webClient.post()
                    .uri(baseUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Service", base.getServiceKey())
                    .bodyValue(baseReq)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<BaseResList<CategoryResDTO>>(){})
                    .timeout(Duration.ofMillis(5000))
                    .block();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        List<CategoryResDTO> result = baseResList.getResult();

        return ResponseEntity.ok(new DataDTO<>(result));
    }

    public ResponseEntity<DataDTO<PaymentResDTO>> paymentWithToken(PaymentReqDTO paymentReqDTO){
        BaseRes<PaymentResDTO> baseRes;
        BaseReq baseReq = BaseReq.builder()
                                .id(130)
                                .jsonrpc("2.0")
                                .method("payment.token")
                                .params(paymentReqDTO)
                                .build();

        try {
            baseRes = webClient.post()
                    .uri(baseUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Service", base.getServiceKey())
                    .bodyValue(baseReq)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<BaseRes<PaymentResDTO>>(){})
                    .timeout(Duration.ofMillis(5000))
                    .block();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        if (baseRes.getError()!=null) throw new CustomException(baseRes.getError().getMessage());

        PaymentResDTO result = baseRes.getResult();

        return ResponseEntity.ok(new DataDTO<>(result));
    }

}
