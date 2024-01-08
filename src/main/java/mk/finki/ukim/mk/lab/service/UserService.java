package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.Role;
import mk.finki.ukim.mk.lab.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User login(String username, String password);

    User register(String username, String password, String repeatPassword, String name, String surname, LocalDate dateOfBirth, Role role);

    Optional<User> findByUsername(String username);

    UserDetails loadUserByUsername(String s);

}
