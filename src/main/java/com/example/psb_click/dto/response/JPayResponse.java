package com.example.psb_click.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JPayResponse {

    private String transaction_id; // payment_id
    private String status;
    private String status_description;
    private JResInfoDTO info;

}
