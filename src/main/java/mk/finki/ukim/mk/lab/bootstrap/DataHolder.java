package mk.finki.ukim.mk.lab.bootstrap;

import lombok.Getter;
import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component //za da se napravi init na pocetok pri loadanje
@Getter
public class DataHolder {

    // prva zadaca
    //public static List<Balloon> category = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<Balloon> balloons = new ArrayList<>();

    public static List<Order> orders = new ArrayList<>();

    @PostConstruct
    public void init(){

        // prva zadaca

//        category.add(new Balloon("Balon","Napraven"));
//        category.add(new Balloon("Balon2","Naduvan"));
//        category.add(new Balloon("Balon2","Naduvan"));
//        category.add(new Balloon("Balon2","Naduvan"));
//        category.add(new Balloon("Balon2","Naduvan"));
//        category.add(new Balloon("Balon2","Naduvan"));
//        category.add(new Balloon("Balon2","Naduvan"));
//        category.add(new Balloon("Balon2","Naduvan"));
//        category.add(new Balloon("Balon2","Naduvan"));
//        category.add(new Balloon("Balon2","Naduvan"));

//        Manufacturer manufacturer = new Manufacturer("Qualatex","USA","Kansas");
//        manufacturers.add(manufacturer);
//        Manufacturer manufacturer1 = new Manufacturer("Creative Balloons Manufacturing","USA","California");
//        manufacturers.add(manufacturer1);
//        Manufacturer manufacturer2 = new Manufacturer("MSR Balloons","USA","Washington");
//        manufacturers.add(manufacturer2);
//        Manufacturer manufacturer3 = new Manufacturer("Balloon Fast","USA","New York");
//        manufacturers.add(manufacturer3);
//        Manufacturer manufacturer4 = new Manufacturer("Balloons Everywhere","USA","Alabama");
//        manufacturers.add(manufacturer4);
//        balloons.add(new Balloon("Balloon","BlueBalloon",manufacturer));
//        balloons.add(new Balloon("Balloon1","BlueBalloon",manufacturer1));
//        balloons.add(new Balloon("Balloon2","BlueBalloon",manufacturer2));
//        balloons.add(new Balloon("Balloon3","BlueBalloon",manufacturer3));
//        balloons.add(new Balloon("Balloon4","BlueBalloon",manufacturer4));
//        balloons.add(new Balloon("Balloon2","BlueBalloon",manufacturer1));
//        balloons.add(new Balloon("Balloon5","BlueBalloon",manufacturer));
//        balloons.add(new Balloon("Balloon1","BlueBalloon",manufacturer2));
//        balloons.add(new Balloon("Balloon2","BlueBalloon",manufacturer3));
//        balloons.add(new Balloon("Balloon10","BlueBalloon",manufacturer4));
    }

}
