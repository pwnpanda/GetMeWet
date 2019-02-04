package com.example.getmewet.repositories;

import com.example.getmewet.models.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusService extends CrudRepository<Status, Integer> {
}
