package com.example.commonmodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
abstract public class BaseEntityDTO<ID extends Serializable> implements Serializable {

    abstract protected ID getId();

    protected Boolean deleted;

    protected Date createdDate;

    protected UserDTO createdBy;

    protected Date lastModifiedDate;

    protected UserDTO lastModifiedBy;

    @JsonIgnore
    protected Long version;

    @JsonProperty("version")
    public Long getVersion() {
        return version;
    }

    @JsonIgnore
    public void setVersion(Long version) {
        this.version = version;
    }

    @JsonIgnore
    public Boolean getDeleted() {
        return deleted;
    }

//    ********************************

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public UserDTO getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserDTO createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public UserDTO getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(UserDTO lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
