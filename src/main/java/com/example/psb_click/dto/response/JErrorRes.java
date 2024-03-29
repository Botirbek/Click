package com.example.psb_click.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JErrorRes {
    private Double code;

    private String message;

    private String traceId;
}
