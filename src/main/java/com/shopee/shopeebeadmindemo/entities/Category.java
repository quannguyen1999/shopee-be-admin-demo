package com.shopee.shopeebeadmindemo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "Category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Category extends CommonBaseEntities{

    @Id
    private UUID id;

    private String name;

    private String image;

}