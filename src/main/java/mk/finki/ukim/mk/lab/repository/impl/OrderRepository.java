package mk.finki.ukim.mk.lab.repository.impl;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

    public List<Order> findAll(){
        return DataHolder.orders;
    }

    public Optional<Order> findById(Long id){
        return DataHolder.orders.stream().filter(b->b.getOrderId().equals(id)).findFirst();
    }

    public Optional<Order> save(String balloonColor, String balloonSize, String clientName, String clientAddress){
        Order order = new Order(balloonColor, balloonSize);
        DataHolder.orders.add(order);
        return Optional.of(order);
    }

    public void deleteById(Long id){
        DataHolder.orders.removeIf(b->b.getOrderId().equals(id));
    }

    public void clearOrders(){
        DataHolder.orders.clear();
    }
}
