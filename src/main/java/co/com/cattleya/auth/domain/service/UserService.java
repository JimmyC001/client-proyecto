package co.com.cattleya.auth.domain.service;

import co.com.cattleya.auth.domain.model.User;
import co.com.cattleya.auth.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    public User saveUser(User user){
        User dbUser = repository.findByUsername(user.getUsername()).orElse(null);
        if(dbUser != null)
            return null;
        return repository.save(user);
    }
    public User findUser(String username){
        return repository.findByUsername(username).orElse(null);
    }
}
