package com.naveen.spring.Repository;

import com.naveen.spring.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo  extends JpaRepository<User,Long> {


}
