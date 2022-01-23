package com.testProject.onlineLibrary.repo;

import com.testProject.onlineLibrary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
