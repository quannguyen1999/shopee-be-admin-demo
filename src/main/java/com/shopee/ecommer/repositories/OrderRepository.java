package com.shopee.ecommer.repositories;

import com.shopee.ecommer.entities.OrderEcommer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEcommer, UUID> {

}
