package com.shaoqin.ez_take_out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shaoqin.ez_take_out.common.BaseContext;
import com.shaoqin.ez_take_out.entity.ShoppingCart;
import com.shaoqin.ez_take_out.mapper.ShoppingCartMapper;
import com.shaoqin.ez_take_out.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: ShoppingCartServiceImpl
 * Package: com.shaoqin.ez_take_out.service.impl
 * Description:
 * Author Shaoqin
 * Create 6/29/23 3:28 PM
 * Version 1.0
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

    @Override
    @Transactional
    public ShoppingCart saveItem(ShoppingCart shoppingCart) {
        ShoppingCart existingCartItem = this.getExistingCartItem(shoppingCart);
        if (existingCartItem != null) {
            existingCartItem.setNumber(existingCartItem.getNumber() + 1);
            this.updateById(existingCartItem);
        } else {
            shoppingCart.setNumber(1);
            this.save(shoppingCart);
            existingCartItem = shoppingCart;
        }

        return existingCartItem;
    }

    @Override
    public List<ShoppingCart> getList() {
        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrendId())
                .orderByDesc(ShoppingCart::getCreateTime);
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public void clearCart() {
        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrendId());
        this.remove(lambdaQueryWrapper);
    }

    @Override
    public void sub(ShoppingCart shoppingCart) {
        ShoppingCart existingCartItem = this.getExistingCartItem(shoppingCart);
        Integer number = existingCartItem.getNumber();
        if (number - 1 == 0) {
            this.removeById(existingCartItem.getId());
        } else {
            existingCartItem.setNumber(number - 1);
            this.updateById(existingCartItem);
        }
    }

    @Override
    public ShoppingCart getExistingCartItem(ShoppingCart shoppingCart) {
        shoppingCart.setUserId(BaseContext.getCurrendId());

        Long dishId = shoppingCart.getDishId();
        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ShoppingCart::getUserId, shoppingCart.getUserId());
        if (dishId != null) {
            lambdaQueryWrapper.eq(ShoppingCart::getDishId, dishId);
        } else {
            lambdaQueryWrapper.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        }
        return this.getOne(lambdaQueryWrapper);
    }

}
