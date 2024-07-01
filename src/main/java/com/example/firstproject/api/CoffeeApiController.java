package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeForm;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CoffeeApiController {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @GetMapping("/api/coffees")
    public ArrayList<Coffee> show() {
        return coffeeRepository.findAll();
    }
    @GetMapping("/api/coffees/{id}")
    public Coffee index(@PathVariable Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    @PostMapping("/api/coffees")
    public Coffee create(@RequestBody CoffeeForm dto) {
        Coffee coffee = dto.toEntity();
        return coffeeRepository.save(coffee);
    }
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm dto) {
        Coffee coffee = dto.toEntity();
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if(target == null || coffee.getId() != id) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        target.patch(coffee);
        Coffee updated = coffeeRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if(target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        coffeeRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
