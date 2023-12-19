package com.shopee.shopeebeadmindemo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "Product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product extends CommonBaseEntities{

    @Id
    private UUID id;

    private String name;

    private String image;

    private Double quantity;

    private Double price;

    private Double discount;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;
}
