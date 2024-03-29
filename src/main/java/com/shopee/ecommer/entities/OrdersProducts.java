package com.shopee.ecommer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.UUID;


@EqualsAndHashCode
@Embeddable
public class OrdersProducts implements Serializable {
    @Column(name = "OrderID")
    private UUID orderID;

    @Column(name = "productID")
    private UUID productID;

}
