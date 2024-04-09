package com.shopee.ecommer.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "Supplier")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Supplier extends CommonBaseEntities {
    @Id
    private UUID id;

    private String phone;

    private String address;

    private String companyName;
}
