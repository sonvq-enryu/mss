package com.mss.product.dto.pagination;

import lombok.Data;

public class Pagination {

    @Data
    public static class BaseRequest {
        private Integer page;
        private Integer size;
    }

    @Data
    public static class BaseResponse<T> {
        private Integer totalPage;
        private T data;
    }
}
