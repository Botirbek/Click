package com.example.psb_click.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTokenReqDTO {
    private String card_number;
    private String expire_date;
    private String phone_number;
}
