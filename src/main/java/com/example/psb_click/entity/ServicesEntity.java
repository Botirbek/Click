package com.example.psb_click.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class ServicesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer service_id;
    private String name;
    private Integer category_id;
    private Integer status;

    private String image;
    private Long min_limit;
    private Long max_limit;

    @ElementCollection
    private List<String> card_types;

//    private Integer priority;
//    private Boolean maintenance;
//    private String web_view_url;
//    private Integer version;
//    private Integer api_version;
    private Boolean active;
}
