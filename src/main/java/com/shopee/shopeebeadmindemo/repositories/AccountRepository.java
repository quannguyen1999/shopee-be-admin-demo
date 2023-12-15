package com.shopee.shopeebeadmindemo.repositories;

import com.shopee.shopeebeadmindemo.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Account findByUsername(String userName);

}
