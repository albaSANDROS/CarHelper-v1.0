package com.example.CarHelper.repos;

import com.example.CarHelper.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Integer> {
}
