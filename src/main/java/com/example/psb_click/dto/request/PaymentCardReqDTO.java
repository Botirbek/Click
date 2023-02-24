package com.example.psb_click.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentCardReqDTO {
    private String card_number;
    private String expire_date;
    private Integer service_id;
    private PaymentParamsReqDTO parameters;
    private String external_id;
}
