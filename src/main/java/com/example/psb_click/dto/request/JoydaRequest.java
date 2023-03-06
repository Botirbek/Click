package com.example.psb_click.dto.request;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Builder
public class JoydaRequest<T> {

    @NotNull(message = "traceId could not be null")
    private Integer traceId;
    private String method;
    private T params;

}
