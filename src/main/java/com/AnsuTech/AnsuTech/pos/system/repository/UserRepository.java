package com.AnsuTech.AnsuTech.pos.system.repository;

import com.AnsuTech.AnsuTech.pos.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
