package com.shaoqin.ez_take_out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shaoqin.ez_take_out.entity.ShoppingCart;

import java.util.List;

/**
 * ClassName: ShoppingCartService
 * Package: com.shaoqin.ez_take_out.service
 * Description:
 * Author Shaoqin
 * Create 6/29/23 3:26 PM
 * Version 1.0
 */
public interface ShoppingCartService extends IService<ShoppingCart> {

    ShoppingCart saveItem(ShoppingCart shoppingCart);

    List<ShoppingCart> getList();

    void clearCart();

    void sub(ShoppingCart shoppingCart);

    ShoppingCart getExistingCartItem(ShoppingCart shoppingCart);

}
