package com.example.firstproject.repository;

import com.example.firstproject.entity.Coffee;
import org.springframework.data.repository.CrudRepository;
import java.util.*;
public interface CoffeeRepository extends CrudRepository<Coffee,Long> {
    @Override
    ArrayList<Coffee> findAll();
}
