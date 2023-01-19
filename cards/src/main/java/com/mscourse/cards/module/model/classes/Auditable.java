package com.mscourse.cards.module.model.classes;

import java.time.LocalDateTime;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    //Properties
    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime creationDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    
    //Getters
    public String getCreatedBy() { return this.createdBy; }
    public LocalDateTime getCreationDate() { return this.creationDate; }
    public String getLastModifiedBy() { return this.lastModifiedBy; }
    public LocalDateTime getLastModifiedDate() { return this.lastModifiedDate; }


    //Setters
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }   
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }
    public void setLastModifiedBy(String lastModifiedBy) { this.lastModifiedBy = lastModifiedBy; }
    public void setLastModifiedDate(LocalDateTime lastModifiedDate) { this.lastModifiedDate = lastModifiedDate; }
    
}
