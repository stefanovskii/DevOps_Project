package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.exceptions.BalloonNotFoundException;
import mk.finki.ukim.mk.lab.model.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.mk.lab.repository.impl.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.jpa.BalloonRepositoryJpa;
import mk.finki.ukim.mk.lab.repository.jpa.ManufacturerRepositoryJpa;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final BalloonRepositoryJpa balloonRepository;
    private final ManufacturerRepositoryJpa manufacturerService;

    public BalloonServiceImpl(BalloonRepositoryJpa balloonRepository, ManufacturerRepositoryJpa manufacturerService) {
        this.balloonRepository = balloonRepository;
        this.manufacturerService = manufacturerService;
    }

    @Override
    public List<Balloon> listAll(){
        return balloonRepository.findAll();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text){
        return balloonRepository.findAllByNameOrDescription(text,text);
    }

    @Override
    public Optional<Balloon> findById(Long id) {
        return this.balloonRepository.findById(id);
    }

    @Override
    public Optional<Balloon> save(String name, String description, Long manufacturerId) {
        Manufacturer manufacturer = this.manufacturerService.findById(manufacturerId)
                .orElseThrow(()->new ManufacturerNotFoundException(manufacturerId));
        return Optional.of(this.balloonRepository.save(new Balloon(name,description,manufacturer)));
    }
    @Override
    public void deleteById(Long id) {
        this.balloonRepository.deleteById(id);
    }

    @Override
    public Optional<Balloon> edit(Long id, String name, String description, Long manufacturer) {
        Balloon balloon = this.balloonRepository.findById(id)
                .orElseThrow(() -> new BalloonNotFoundException(id));
        balloon.setName(name);
        balloon.setDescription(description);

        Manufacturer manufacturer1 = this.manufacturerService.findById(manufacturer)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturer));
        balloon.setManufacturer(manufacturer1);

        return Optional.of(this.balloonRepository.save(balloon));
    }

    @Override
    public Balloon create(String name, String description) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Balloon b = new Balloon(name,description);
        balloonRepository.save(b);
        return b;
    }


}
