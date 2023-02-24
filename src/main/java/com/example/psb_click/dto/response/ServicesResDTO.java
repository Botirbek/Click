package com.example.psb_click.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicesResDTO {
    private Integer id;
    private String name;
//    private Integer category_id;
    private Integer status;
//    private Integer priority;
    private String image;
    private Integer min_limit;
    private Integer max_limit;
    private String[] card_types;
//    private Boolean maintenance;
//    private String web_view_url;
//    private Integer version;
//    private Integer api_version;

}
