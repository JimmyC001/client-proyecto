package co.com.cattleya.auth.domain.service;

import co.com.cattleya.auth.domain.model.User;
import co.com.cattleya.auth.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;
    @Autowired
    public UserService(UserRepository repository){
        this.repository = repository;
    }
    public User save(User user){
        User dbUser = repository.findByUsername(user.getUsername());
        if(dbUser == null)
            return user;
        return repository.save(user);
    }
    public User findByUsername(String username){
        return repository.findByUsername(username);
    }
}
