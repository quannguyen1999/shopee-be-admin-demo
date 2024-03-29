package com.shopee.ecommer.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "OrderEcommer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldNameConstants
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

    @OneToMany
    private List<OrderDetail> orderDetailList = new ArrayList<>();
}
