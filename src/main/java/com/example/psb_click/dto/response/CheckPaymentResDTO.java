package com.example.psb_click.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckPaymentResDTO {
    private String payment_id;
    private Integer payment_status;
    private String status_description;
    private String dateTime;
}
