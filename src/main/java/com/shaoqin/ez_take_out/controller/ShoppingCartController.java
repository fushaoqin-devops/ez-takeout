package com.shaoqin.ez_take_out.controller;

import com.shaoqin.ez_take_out.common.R;
import com.shaoqin.ez_take_out.entity.ShoppingCart;
import com.shaoqin.ez_take_out.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: ShoppingCartController
 * Package: com.shaoqin.ez_take_out.controller
 * Description:
 * Author Shaoqin
 * Create 6/29/23 3:24 PM
 * Version 1.0
 */
@RestController
@RequestMapping("/shoppingCart")
@Slf4j
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart) {
        ShoppingCart savedItem = shoppingCartService.saveItem(shoppingCart);
        return R.success(savedItem);
    }

    @PostMapping("/sub")
    public R<String> sub(@RequestBody ShoppingCart shoppingCart) {
        shoppingCartService.sub(shoppingCart);
        return R.success("Cart updated");
    }

    @GetMapping("/list")
    public R<List<ShoppingCart>> list() {
        List<ShoppingCart> list = shoppingCartService.getList();
        return R.success(list);
    }

    @DeleteMapping("/clean")
    public R<String> clear() {
        shoppingCartService.clearCart();
        return R.success("Cart cleared");
    }

}
