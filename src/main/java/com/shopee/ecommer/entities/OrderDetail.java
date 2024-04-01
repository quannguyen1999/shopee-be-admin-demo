package com.shopee.ecommer.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Columns;
import org.springframework.core.annotation.Order;

import java.util.UUID;

@Entity
@Table(name = "order_detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetail extends CommonBaseEntities {


    @Id
    public UUID id;

    public UUID orderId;

    public UUID productId;

    public Double discount;

    public int quantity;

    public Double totalAmount;

}
