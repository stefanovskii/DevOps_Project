package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class OrderAlreadyInShoppingCartException extends RuntimeException{
    public OrderAlreadyInShoppingCartException(Long id, String username){
        super(String.format("Product with id %d already exists in ShoppingCart for user with username %s"
                ,id,username));
    }
}
