package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final BalloonService balloonService;
    private final OrderService orderService;


    public ShoppingCartController(ShoppingCartService shoppingCartService, BalloonService balloonService, OrderService orderService) {
        this.shoppingCartService = shoppingCartService;
        this.balloonService = balloonService;
        this.orderService = orderService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username = req.getRemoteUser();
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);
        model.addAttribute("orders", this.shoppingCartService.listAllOrdersInShoppingCart(shoppingCart.getId()));
        //model.addAttribute("balloons",this.orderService.listAllOrdersInShoppingCart(shoppingCart.getId()));
        return "shopping-cart";
    }

    @PostMapping("/add-order/{id}")
    public String addOrderToShoppingCart(@PathVariable Long id, HttpServletRequest request,
                                         Authentication authentication) {
        try {
            String username = request.getRemoteUser();
            ShoppingCart shoppingCart = this.shoppingCartService.addOrdersToShoppingCart(username,id);
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }

}
