package com.example.psb_click.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include. NON_NULL)
public class BaseReq<T> {
    private String jsonrpc;
    private String method;
    private Integer id;
    private T params;
}
