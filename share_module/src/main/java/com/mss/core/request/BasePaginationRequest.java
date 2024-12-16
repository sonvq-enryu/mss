package com.mss.core.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePaginationRequest {
    private int page;
    private int pageNumber;
    private String keyword;
    private String orderBy;
}
