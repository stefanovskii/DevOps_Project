package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.exceptions.OrderAlreadyInShoppingCartException;
import mk.finki.ukim.mk.lab.model.exceptions.OrderNotFoundException;
import mk.finki.ukim.mk.lab.model.exceptions.ShoppingCartNotFound;
import mk.finki.ukim.mk.lab.model.exceptions.UserNotFoundException;
import mk.finki.ukim.mk.lab.repository.jpa.OrderRepositoryJpa;
import mk.finki.ukim.mk.lab.repository.jpa.ShoppingCartRepositoryJpa;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepositoryJpa;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepositoryJpa shoppingCartRepository;
    private final UserRepositoryJpa userRepository;
    private final OrderRepositoryJpa productService;

    public ShoppingCartServiceImpl(ShoppingCartRepositoryJpa shoppingCartRepository, UserRepositoryJpa userRepository, OrderRepositoryJpa productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }


    @Override
    public List<Order> listAllOrdersInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepository.findById(cartId).isPresent()){
            throw new ShoppingCartNotFound(cartId);
        }
        return this.shoppingCartRepository.findById(cartId).get().getOrders();

    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {

        User user = this.userRepository.findByUsername(username)
                .orElseThrow(()-> new UserNotFoundException(username));
        return this.shoppingCartRepository
                .findByUser(user)
                .orElseGet(()->{
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
    }

    @Override
    public ShoppingCart addOrdersToShoppingCart(String username, Long orderId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Order order = this.productService.findById(orderId)
                .orElseThrow(()->new OrderNotFoundException(orderId));
        if(shoppingCart.getOrders().stream()
                .filter(r->r.getOrderId().equals(orderId))
                .collect(Collectors.toList()).size() > 0){
            throw new OrderAlreadyInShoppingCartException(orderId,username);
        }
        shoppingCart.getOrders().add(order);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}
