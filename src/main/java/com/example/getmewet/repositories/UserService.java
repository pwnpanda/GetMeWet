package com.example.getmewet.repositories;

import com.example.getmewet.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserService extends CrudRepository<User, Integer> {
}
