package com.eatbook.backoffice.entity.base;

import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@MappedSuperclass
@SQLDelete(sql = "UPDATE ${hibernate.entityName} SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public abstract class SoftDeletableEntity extends BaseEntity {

    private LocalDateTime deletedAt;

    public boolean isDeleted() {
        return deletedAt != null;
    }

    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
    }

    public void restore() {
        this.deletedAt = null;
    }
}
