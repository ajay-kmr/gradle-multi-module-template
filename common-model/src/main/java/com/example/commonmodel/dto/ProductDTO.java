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

//    ********************************

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }
}
