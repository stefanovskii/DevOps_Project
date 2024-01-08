package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.exceptions.ShoppingCartNotFoundException;
import mk.finki.ukim.mk.lab.repository.impl.OrderRepository;
import mk.finki.ukim.mk.lab.repository.jpa.OrderRepositoryJpa;
import mk.finki.ukim.mk.lab.repository.jpa.ShoppingCartRepositoryJpa;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepositoryJpa orderRepository;
    private final ShoppingCartRepositoryJpa shoppingCartRepositoryJpa;

    public OrderServiceImpl(OrderRepositoryJpa orderRepository, ShoppingCartRepositoryJpa shoppingCartRepositoryJpa) {
        this.orderRepository = orderRepository;
        this.shoppingCartRepositoryJpa = shoppingCartRepositoryJpa;
    }

    @Override
    public Optional<Order> placeOrder(String balloonColor, String balloonSize, String clientName, String address){
        return Optional.of(this.orderRepository.save(new Order(balloonColor, balloonSize)));
    }

    @Override
    public List<Order> listAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public void clearOrders() {
        this.orderRepository.deleteAll();
    }

    @Override
    public Optional<Order> save(String color, String size) {
        return Optional.of(this.orderRepository.save(new Order(color,size)));
    }
    @Override
    public Optional<Order> save(String color, String size, LocalDateTime orderDate) {
        return Optional.of(this.orderRepository.save(new Order(color,size, orderDate)));
    }

    @Override
    public List<Order> listAllOrdersInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepositoryJpa.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRepositoryJpa.findById(cartId).get().getOrders();
    }


}
