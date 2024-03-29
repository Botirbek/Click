package com.example.psb_click.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormReqDTO {

    @NotNull(message = "stepga narsa junat")
    @Max(message = "Step 10 dan kichik bolmasin", value = 10)
    private Integer step;

    @NotNull(message = "service_id ga narsa junat")
    @Min(message = "service_id 20 dan katta bolmasin", value = 20)
    private Integer service_id;

    private Object data;

}


