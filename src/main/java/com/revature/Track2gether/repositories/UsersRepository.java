package com.revature.Track2gether.repositories;

import com.revature.Track2gether.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {

    public abstract Users findByEmailAndPassword(String email, String password);

    //public abstract List<Users> findUserById(String user_id);
}
