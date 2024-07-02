package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeForm;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    public ArrayList<Coffee> show() {
        return coffeeRepository.findAll();
    }

    public Coffee index(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeForm dto) {
        Coffee coffee = dto.toEntity();
        return coffeeRepository.save(coffee);
    }

    public Coffee update(CoffeeForm dto, Long id) {
        Coffee coffee = dto.toEntity();
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if (target == null || coffee.getId() != id) {
            return null;
        }
        target.patch(coffee);
        Coffee updated = coffeeRepository.save(target);
        return updated;
    }
    public Coffee delete(Long id) {
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if (target == null) {
            return null;
        }
        coffeeRepository.delete(target);
        return target;
    }
}
