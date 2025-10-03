package com.AnsuTech.AnsuTech.pos.system.repository;

import com.AnsuTech.AnsuTech.pos.system.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByAdminId(Long id);
}
