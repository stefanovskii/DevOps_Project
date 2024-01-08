package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="Orders")
public class Order {

    private String balloonColor;
    private String baloonSize;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private LocalDateTime dateTime;

    public Order(String balloonColor, String baloonSize) {
        this.balloonColor = balloonColor;
        this.baloonSize = baloonSize;
        this.dateTime = LocalDateTime.now();
    }
    public Order(String balloonColor, String balloonSize, LocalDateTime dateTime) {
        this.balloonColor = balloonColor;
        this.baloonSize = balloonSize;
        this.dateTime = dateTime;
    }

    public Order() {}
}
