package com.example.getmewet.repositories;

import com.example.getmewet.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Integer> {
    User findById(int id);
    User findByUsername(String username);
}