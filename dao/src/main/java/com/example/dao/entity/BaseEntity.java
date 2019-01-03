package com.example.dao.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
abstract public class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    protected Boolean deleted = false;
    @CreatedDate
    protected Date createdDate;
    @CreatedBy
    @OneToOne(fetch = FetchType.LAZY)
    protected User createdBy;
    @LastModifiedDate
    protected Date lastModifiedDate;
    @LastModifiedBy
    @OneToOne(fetch = FetchType.LAZY)
    protected User lastModifiedBy;
    @Version
    protected Long version;

    abstract public T getId();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity<?> that = (BaseEntity<?>) o;
        if (getId() == null || that.getId() == null) {
            return false;
        }

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 31;
    }
}
