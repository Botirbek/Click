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
public class TransferToTokenReqDTO {
    private String card_token;
    private String card_token_receiver;
    private BigDecimal amount;
    private String external_id;
}
