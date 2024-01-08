package mk.finki.ukim.mk.lab.service;


import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<Order> listAllOrdersInShoppingCart(Long cartId);

    ShoppingCart getActiveShoppingCart(String username);

    ShoppingCart addOrdersToShoppingCart(String username, Long orderId);

}
