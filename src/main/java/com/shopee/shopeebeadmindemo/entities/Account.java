package com.shopee.shopeebeadmindemo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Account")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Account extends CommonBaseEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String username;

    private String password;

    private Date birthday;

    private Boolean gender;

    private String email;

    private String avatar;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoleAccount> roleAccountList = new ArrayList<RoleAccount>();
}
