package com.mss.product.model.postgres;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product extends AbstractAuditEntity {
    @Id
    @UuidGenerator
    private UUID id;

    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
}
