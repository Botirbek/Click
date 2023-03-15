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

    @NotNull(message = "id for request could not be null")
    private Integer id;

    @NotNull(message = "trace_id could not be null")
    private String trace_id;

    private Integer service_id;

    private T params;

}
