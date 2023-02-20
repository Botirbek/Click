package com.example.psb_click.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseRes<T> {
    private String jsonrpc;
    private Integer id;
    private T result;
    private ErrorRes error;
}

