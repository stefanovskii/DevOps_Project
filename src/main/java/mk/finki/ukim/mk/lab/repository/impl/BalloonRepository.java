package mk.finki.ukim.mk.lab.repository.impl;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {

    public List<Balloon> findAllBalloons(){
        return DataHolder.balloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text){
        return DataHolder.balloons.stream().filter(r->r.getName().equals(text) ||
                r.getDescription().equals(text)).collect(Collectors.toList());
    }

    public Optional<Balloon> findById(Long id){
        return DataHolder.balloons.stream().filter(r->r.getId().equals(id)).findFirst();
    }

    public Optional<Balloon> save(String name, String description, Manufacturer manufacturer){
        DataHolder.balloons.removeIf(r->r.getName().equals(name));
        Balloon balloon = new Balloon(name,description,manufacturer);
        DataHolder.balloons.add(balloon);
        return Optional.of(balloon);
    }

    public void deleteById(Long id){
        DataHolder.balloons.removeIf(r->r.getId().equals(id));
    }

}
