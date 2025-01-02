package com.lucas.embeddedh2test.repository;


import com.lucas.embeddedh2test.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
}
