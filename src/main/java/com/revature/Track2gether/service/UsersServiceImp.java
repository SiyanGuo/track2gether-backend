package com.revature.Track2gether.service;

import com.revature.Track2gether.dto.SignUpDTO;
import com.revature.Track2gether.dto.Transactiondto;
import com.revature.Track2gether.model.Transaction;
import com.revature.Track2gether.model.Users;
import com.revature.Track2gether.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
@Service
public class UsersServiceImp implements UserService {

    @Autowired
    private UsersRepository userrepo;

    private SignUpDTO convertUsersEntitytoDTO(Users user){
        SignUpDTO sdto = new SignUpDTO();
        sdto.setId(user.getId());
        sdto.setFirstName(user.getFirstname());
        sdto.setLastName(user.getLastname());
        sdto.setEmail(user.getEmail());
        sdto.setPassword(user.getPassword());
        return sdto;

    }

    @Override
    public SignUpDTO addUser(Users user) {
        Users newUser = userrepo.save(user);
        SignUpDTO sdto = new SignUpDTO();
        sdto = convertUsersEntitytoDTO(newUser);
        return sdto;
    }

    @Override
    public Users getUserById(int id) {
        Users user = userrepo.getById(id);
        return user;

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