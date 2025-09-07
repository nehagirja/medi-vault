package com.neu.edu.service;

import com.neu.edu.model.Users;
import com.neu.edu.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CommonService {

    @Autowired
    private UsersRepo usersRepo;

    public Users findUserByHashId(String userHashId){
        Optional<Users> users = usersRepo.findById(UUID.fromString(userHashId));
        if(users.isEmpty()){
            throw new NullPointerException("User not found");
        }
        return users.get();
    }
}
