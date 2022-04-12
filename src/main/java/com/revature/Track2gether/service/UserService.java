package com.revature.Track2gether.service;

import com.revature.Track2gether.dto.SignUpDTO;
import com.revature.Track2gether.model.Users;

import java.util.List;

public interface UserService {

    public SignUpDTO addUser(Users user);

    public Users getUserById(int id);

    public List<Users> getAll();

    public Users UpdateUsers(Users user);

    public void deleteUsersById(int id);
}