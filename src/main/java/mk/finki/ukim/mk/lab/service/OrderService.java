package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<Order> placeOrder(String balloonColor, String balloonsize, String clientName, String address);

    List<Order> listAll();

    void clearOrders();
    Optional<Order> save(String color, String size);

    Optional<Order> save(String color, String size, LocalDateTime orderDate);

    List<Order> listAllOrdersInShoppingCart(Long cartId);
}
