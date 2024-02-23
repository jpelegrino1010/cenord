package com.julioluis.noahrdsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @JsonIgnore
    @CreatedDate
    @Column(updatable = false)
    protected LocalDateTime createdAt;
    @JsonIgnore
    @CreatedBy
    @Column(updatable = false)
    protected String createdBy;
    @JsonIgnore
    @LastModifiedDate
    @Column(insertable = false)
    protected LocalDateTime updatedAt;
    @JsonIgnore
    @LastModifiedBy
    @Column(insertable = false)
    protected String updatedBy;
}