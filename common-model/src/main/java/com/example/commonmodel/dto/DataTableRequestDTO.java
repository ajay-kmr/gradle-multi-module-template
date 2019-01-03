package com.example.commonmodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataTableRequestDTO<T> {

    private static final String ASC = "asc";
    private static final String defaultSortColumn = "createdDate";

    private String sortColumn;
    private String sortOrder;
    private Integer pageSize;
    private Integer pageIndex;
    private Boolean fetchAllRecords = Boolean.FALSE;

    private T query;


    public Integer getPageSize() {
        if ((pageSize == null) || (pageSize <= 0)) {
            return 10;
        }
        return pageSize;
    }

    public Integer getPageIndex() {
        if ((pageIndex == null) || (pageIndex < 0)) {
            return 0;
        }
        return pageIndex;
    }

    @JsonIgnore
    public Integer getMax() {
        return getPageSize();
    }

    public Integer getOffset() {
        return getPageIndex() * getPageSize();
    }
}
