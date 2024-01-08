package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name="Users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    @Convert(converter = UserFullnameConverter.class)
    private UserFullname userFullname;
    private String password;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @Enumerated(value=EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    public User(String username, String name, String surname, String password,LocalDate dateOfBirth,Role role) {
        this.username = username;
        this.userFullname = new UserFullname(name, surname);
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.carts = new ArrayList<>();
        this.role = role;
    }
//    public User(String username,String name,String surname, String password, Role role){
//        this.username = username;
//        this.userFullname = new UserFullname(name,surname);
//        this.password = password;
//        this.role = role;
//    }

    public User() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
