package com.shopee.ecommer.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "OrderDetail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetail extends CommonBaseEntities {
    @EmbeddedId
    OrdersProducts id;

    private Double discount;

    private int quantity;

    private int totalAmount;

}
