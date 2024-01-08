package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Balloon {
    private String name;
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Manufacturer manufacturer;

    // za prva zadaca
    public Balloon(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // za vtora zadaca
    public Balloon(String name, String description, Manufacturer manufacturer) {
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public Balloon() {}
}
