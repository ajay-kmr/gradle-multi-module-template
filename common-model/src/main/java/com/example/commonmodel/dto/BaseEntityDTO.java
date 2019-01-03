package com.example.commonmodel.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
abstract public class BaseEntityDTO<ID extends Serializable> implements Serializable {

    protected Boolean deleted;
    protected Date createdDate;
    protected UserDTO createdBy;
    protected Date lastModifiedDate;
    protected UserDTO lastModifiedBy;

    abstract protected ID getId();
}
