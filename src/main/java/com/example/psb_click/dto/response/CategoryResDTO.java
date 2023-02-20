package com.example.psb_click.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResDTO {
    private Integer id;
    private String name;
    private Integer priority;
    private Boolean status;
}
