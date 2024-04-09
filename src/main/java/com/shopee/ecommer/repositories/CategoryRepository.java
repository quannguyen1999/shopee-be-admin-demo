package com.shopee.ecommer.repositories;

import com.shopee.ecommer.entities.Category;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @NotNull
    Optional<Category> findById(@NotNull UUID id);

}
