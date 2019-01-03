package com.example.commonmodel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataTableResponseDTO<Q, T> {

    private static final String ASC = "asc";
    private static final String defaultSortColumn = "createdDate";
    /**
     * recordsTotal is the total records in the table of database.
     */
    protected long recordsTotal;
    /**
     * recordsFiltered is the total number of records which meet current search criteria.
     */

    protected long recordsFiltered;
    private String sortColumn;
    private String sortOrder;
    private Integer pageSize;
    private Integer pageIndex;
    private Boolean fetchAllRecords = Boolean.FALSE;
    private Boolean status = true;
    private String message;
    private T data;
    private List<ErrorDTO> errors = new ArrayList<>();
    /**
     * This is the query against which current response is generated
     * It is optional
     */
    private Q query;

    public static <Q, T> DataTableResponseDTO<Q, List<T>> getInstance(long recordsTotal, List<T> data, long recordsFiltered,
                                                                      DataTableRequestDTO<Q> dataTableRequestDTO) {
        DataTableResponseDTO<Q, List<T>> dataTableResponseDTO = new DataTableResponseDTO<>();
        dataTableResponseDTO.setPageIndex(dataTableRequestDTO.getPageIndex());
        dataTableResponseDTO.setPageSize(dataTableRequestDTO.getPageSize());
        dataTableResponseDTO.setSortColumn(dataTableRequestDTO.getSortColumn());
        dataTableResponseDTO.setSortOrder(dataTableRequestDTO.getSortOrder());
        dataTableResponseDTO.setQuery(dataTableRequestDTO.getQuery());
        return setInstance(recordsTotal, data, recordsFiltered, dataTableResponseDTO);
    }

    public static <Q, T> DataTableResponseDTO<Q, List<T>> getInstance(long recordsTotal, List<T> data, long recordsFiltered) {
        DataTableResponseDTO<Q, List<T>> dataTableResponseDTO = new DataTableResponseDTO<Q, List<T>>();
        return setInstance(recordsTotal, data, recordsFiltered, dataTableResponseDTO);
    }

    private static <Q, T> DataTableResponseDTO<Q, List<T>> setInstance(long recordsTotal,
                                                                       List<T> data,
                                                                       long recordsFiltered,
                                                                       DataTableResponseDTO<Q, List<T>> dataTableResponseDTO) {
        if (dataTableResponseDTO == null) {
            dataTableResponseDTO = new DataTableResponseDTO<>();
        }
        dataTableResponseDTO.setRecordsTotal(recordsTotal);
        dataTableResponseDTO.setData(data);
        dataTableResponseDTO.setRecordsFiltered(recordsFiltered);
        dataTableResponseDTO.setStatus(true);
        if (CollectionUtils.isEmpty(data)) {
            dataTableResponseDTO.setMessage("No record found");
        } else {
            dataTableResponseDTO.setMessage("Request processed successfully");
        }
        return dataTableResponseDTO;
    }

    public static <Q, T> DataTableResponseDTO<Q, List<T>> getInstance(Boolean status, String message) {
        DataTableResponseDTO<Q, List<T>> dataTableResponseDTO = new DataTableResponseDTO<>();
        dataTableResponseDTO.setStatus(status);
        dataTableResponseDTO.setMessage(message);
        return dataTableResponseDTO;
    }
}
