package com.mss.core.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationResponse<T> {
    private int page;
    private int pageNumber;
    private long count;
    private T data;
}
