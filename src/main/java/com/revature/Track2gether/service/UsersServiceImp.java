package com.revature.Track2gether.service;

import com.revature.Track2gether.exception.BadParameterException;
import com.revature.Track2gether.model.Users;
import com.revature.Track2gether.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
@Service
public class UsersServiceImp implements UserService {

    @Autowired
    private UsersRepository userrepo;

    @Override
    @Transactional
    public Users createUsers(Users user) {
        Users newUser = userrepo.save(user);
        return newUser;
    }

    @Override
    public Users getUserById(int id) throws EntityNotFoundException {
        try {
            Users user = userrepo.getById(id);
            return user;

        }catch (Exception e){
            throw new EntityNotFoundException("User not found....");
        }

    }

    @Override
    public List<Users> getAll() {
        return null;
    }

    @Override
    public Users UpdateUsers(Users c) {
        return null;
    }

    @Override
    public void deleteUsersById(int id) {

    }
}
