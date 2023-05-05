package com.titzko.freemarkertodo.service;

import com.titzko.freemarkertodo.exceptions.UnauthorizedException;
import com.titzko.freemarkertodo.model.ShoppingItem;
import com.titzko.freemarkertodo.repository.ShoppingItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ShoppingService {

    UserService userService;
    ShoppingItemRepository shoppingItemRepository;

    public ShoppingService(UserService userService, ShoppingItemRepository shoppingItemRepository) {
        this.userService = userService;
        this.shoppingItemRepository = shoppingItemRepository;
    }

    public void saveShoppingItems(List<ShoppingItem> items) {
        Long userId = userService.getUser().getId();

        if (items.stream().allMatch(shoppingItem -> Objects.equals(shoppingItem.getUserId(), userId))) {
            this.shoppingItemRepository.saveAll(items);
        } else {
            throw new UnauthorizedException(String.format("User with ID %f tried to perform unauthorised actions", userId));
        }
    }

    public void saveShoppingItem(ShoppingItem item) {
        Long userId = userService.getUser().getId();

        if (Objects.equals(item.getUserId(), userId)) {
            this.shoppingItemRepository.save(item);
        } else {
            throw new UnauthorizedException(String.format("User with ID %f tried to perform unauthorised actions", userId));
        }
    }

    public List<ShoppingItem> getShoppingItems() {
        Long userId = userService.getUser().getId();
        List<ShoppingItem> shoppingItems = this.shoppingItemRepository.findShoppingItemsByUserId(userId);
        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setUserId(userService.getUser().getId());
        return !shoppingItems.isEmpty() ? shoppingItems : List.of(shoppingItem);
    }
}
