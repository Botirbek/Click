package com.example.psb_click.dto.response;

import com.example.psb_click.dto.basic.LanguageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JResInfoDTO {

    private LanguageDTO title;

    private String key;

    private String value;

}
