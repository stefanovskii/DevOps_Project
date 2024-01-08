package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.Role;
import mk.finki.ukim.mk.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.mk.lab.model.exceptions.UsernameExistsException;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepositoryJpa;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepositoryJpa userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepositoryJpa userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }

    @Override
    public User login(String username, String password) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, LocalDate dateOfBirth, Role role) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        if(!password.equals(repeatPassword)){
            throw new PasswordsDoNotMatchException();
        }

        if(this.userRepository.findByUsername(username).isPresent() || !this.userRepository.findByUsername(username).isEmpty())
            throw new UsernameExistsException(username);

        User user = new User(username,name,surname,passwordEncoder.encode(password),dateOfBirth,role);
        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
