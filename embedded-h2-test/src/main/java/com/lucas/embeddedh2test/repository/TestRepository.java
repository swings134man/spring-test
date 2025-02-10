package com.lucas.embeddedh2test.repository;

import com.lucas.embeddedh2test.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
