package com.test.translator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
public class RequestDto {

    private String text;

    @JsonProperty("current_language")
    private String currentLanguage;

    @JsonProperty("target_language")
    private String targetLanguage;
}