package com.mss.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class TokenProps {
    private String sub;
    private Map<String, Object> customClaims;
    private int durationInMinutes;

}
