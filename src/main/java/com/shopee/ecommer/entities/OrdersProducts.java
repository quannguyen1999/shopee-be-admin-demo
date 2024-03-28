package com.shopee.ecommer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class OrdersProducts implements Serializable {
    @Column(name = "OrderID")
    private UUID orderID;

    @Column(name = "productID")
    private UUID productID;

}
