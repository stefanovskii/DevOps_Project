package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Balloon;

import java.util.List;
import java.util.Optional;

public interface BalloonService {

    List<Balloon> listAll();
    List<Balloon> searchByNameOrDescription(String text);
    Optional<Balloon> findById(Long id);
    Optional<Balloon> save(String name,String description,Long id);
    void deleteById(Long id);

    Optional<Balloon> edit(Long id, String name, String description, Long manufacturer);

    Balloon create(String name, String description);

}
