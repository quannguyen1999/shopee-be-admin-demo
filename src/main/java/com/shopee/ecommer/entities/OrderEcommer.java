package com.shopee.ecommer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "OrderEcommer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderEcommer extends CommonBaseEntities {
    @Id
    private UUID id;

    private Date orderDate;

    private String shipCity;

    private Date shippedDate;

    private String shipRegion;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
