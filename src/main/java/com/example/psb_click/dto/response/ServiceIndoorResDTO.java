package com.example.psb_click.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceIndoorResDTO {
    private Integer id;
    private String name;
    private Integer category_id;
    private Integer status;
    private Integer priority;
    private String  image;
    private Long min_limit;
    private Long max_limit;
    private String[] card_types;
//    private Boolean maintenance;
//    private Integer version;
//    private Integer api_version;

}
