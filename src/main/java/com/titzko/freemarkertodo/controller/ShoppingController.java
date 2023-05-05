package com.titzko.freemarkertodo.controller;

import com.titzko.freemarkertodo.model.ShoppingItem;
import com.titzko.freemarkertodo.model.ShoppingItemList;
import com.titzko.freemarkertodo.service.ShoppingService;
import com.titzko.freemarkertodo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shopping")
public class ShoppingController {

    ShoppingService shoppingService;
    UserService userService;

    public ShoppingController(ShoppingService shoppingService, UserService userService) {
        this.shoppingService = shoppingService;
        this.userService = userService;
    }

    @GetMapping("")
    public String getShoppingList(Model model) {
        List<ShoppingItem> shoppingItems = this.shoppingService.getShoppingItems();
        ShoppingItemList shoppingItemList = new ShoppingItemList(shoppingItems);
        model.addAttribute("shoppingItemList", shoppingItemList);
        model.addAttribute("isShopping", true);
        return "shopping/shopping";
    }


    @PostMapping("")
    public String saveShoppingList(ShoppingItemList shoppingItemList) {
        this.shoppingService.saveShoppingItems(shoppingItemList.getShoppingItems());
        return "redirect:/shopping";
    }
}
