package com.naveen.spring.Repository;

import com.naveen.spring.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
  User findByUsername(String username);
}
