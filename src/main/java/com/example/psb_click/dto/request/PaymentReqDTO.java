package com.example.psb_click.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentReqDTO {
    private String card_token;
    private Integer service_id;
    private PaymentParamsReqDTO parameters;
    private String external_id;
}
