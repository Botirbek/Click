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
public class CheckPaymentIdResDTO {
    private String payment_id;
    private Integer payment_status;
    private String status_description;
    private LocalDateTime dateTime;
    private String external_id;
}
