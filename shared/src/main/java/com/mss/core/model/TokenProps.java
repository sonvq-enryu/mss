package com.mss.core.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TokenProps {
    String sub;
    Map<String, Object> customClaims;
    int durationInMinutes;
}
