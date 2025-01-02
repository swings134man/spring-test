package com.lucas.embeddedh2test.service;

import com.lucas.embeddedh2test.model.UsersEntity;
import com.lucas.embeddedh2test.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UsersRepository usersRepository;


    @Transactional
    public UsersEntity saveUser(UsersEntity usersEntity) {
        return usersRepository.save(usersEntity);
    }

    @Transactional(readOnly = true)
    public UsersEntity findById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Transactional
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }
}
