package com.example.psb_click.dto.request;


import com.example.psb_click.dto.basic.Location;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceIndoorReqDTO {
    private Integer category_id;
    private Integer page_number;
    private Integer api_version;
    private Location location;
    private String search;
}


