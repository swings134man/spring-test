package com.lucas.embeddedh2test.service;

import com.lucas.embeddedh2test.exceptions.ResourceNotFoundException;
import com.lucas.embeddedh2test.model.UsersEntity;
import com.lucas.embeddedh2test.repository.UsersRepository;
import com.lucas.embeddedh2test.utils.TestLogUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UsersRepository usersRepository;
    private final TestLogUtil testLogUtil;


    @Transactional
    public UsersEntity saveUser(UsersEntity usersEntity) {
        UsersEntity save = usersRepository.save(usersEntity);
        printLog();
        return save;
    }

    @Transactional(readOnly = true)
    public UsersEntity findById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Users", id));
    }

    @Transactional(readOnly = true)
    public UsersEntity findByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email", email));
    }

    @Transactional
    public UsersEntity update(UsersEntity user) {
        UsersEntity entity = findById(user.getId());
        entity.setNickname(user.getNickname());
        entity.setEmail(user.getEmail());
        entity.setAddress(user.getAddress());
        entity = usersRepository.save(entity);
        return entity;
    }

    @Transactional
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    private void printLog() {
        testLogUtil.print();
    }

}
