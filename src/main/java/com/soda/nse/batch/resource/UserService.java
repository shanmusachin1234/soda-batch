package com.soda.nse.batch.resource;

import com.soda.nse.batch.model.Users;
import com.soda.nse.batch.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/users")
public class UserService {

    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<Users> getAll() {
        return userRepository.findAll();
    }
    @RequestMapping("/{userID}")
    public Optional<Users> getUser(@PathVariable(value="userID") Integer id) {
        return userRepository.findById(id);
    }
}
