package com.ucl.common.util;

import java.util.ArrayList;
import java.util.List;

public class UtilPagingResponse<T> {

    private List<T> list = new ArrayList<>();
    private UtilPaging pagination;

    public UtilPagingResponse(List<T> list, UtilPaging pagination) {
        this.list.addAll(list);
        this.pagination = pagination;
    }

}