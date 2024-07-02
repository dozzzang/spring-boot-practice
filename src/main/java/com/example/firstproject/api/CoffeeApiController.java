package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeForm;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import com.example.firstproject.service.CoffeeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CoffeeApiController {
    CoffeeService coffeeService;
    @GetMapping("/api/coffees")
    public ArrayList<Coffee> show() {
        return coffeeService.show();
    }
    @GetMapping("/api/coffees/{id}")
    public Coffee index(@PathVariable Long id) {
        return coffeeService.index(id);
    }

    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeForm dto) {
        Coffee created = coffeeService.create(dto);
        return (created != null) ?
                 ResponseEntity.status(HttpStatus.OK).body(created) :
                 ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm dto) {
        Coffee created = coffeeService.update(dto,id);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
        Coffee created = coffeeService.delete(id);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(null) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
