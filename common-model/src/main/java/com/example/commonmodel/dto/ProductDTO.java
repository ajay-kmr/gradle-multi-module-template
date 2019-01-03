package com.example.commonmodel.dto;

import com.example.commonmodel.enums.ProductType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDTO extends BaseEntityDTO<Long> {
    protected Long id;
    protected Long catalogId;
    private String name;
    private ProductType type;
    private String catalogName;
}
