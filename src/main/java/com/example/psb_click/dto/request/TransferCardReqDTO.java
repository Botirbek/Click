package com.example.psb_click.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferCardReqDTO {
    private String card_number;
    private String card_number_receiver;
    private String expire_date;
    private BigDecimal amount;
    private String external_id;
}
