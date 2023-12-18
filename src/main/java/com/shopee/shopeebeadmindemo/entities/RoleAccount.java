package com.shopee.shopeebeadmindemo.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RoleAccount")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoleAccount extends CommonBaseEntities {
    @Id
    private String code;

    private String name;

    // This is the foreign key column in the Child table
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
