package com.shaoqin.ez_take_out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shaoqin.ez_take_out.common.BaseContext;
import com.shaoqin.ez_take_out.common.CustomException;
import com.shaoqin.ez_take_out.dto.DishDto;
import com.shaoqin.ez_take_out.dto.OrdersDto;
import com.shaoqin.ez_take_out.dto.PageDto;
import com.shaoqin.ez_take_out.entity.*;
import com.shaoqin.ez_take_out.mapper.OrderMapper;
import com.shaoqin.ez_take_out.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * ClassName: OrderServiceImpl
 * Package: com.shaoqin.ez_take_out.service.impl
 * Description:
 * Author Shaoqin
 * Create 6/29/23 5:47 PM
 * Version 1.0
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressBookService addressBookService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    @Transactional
    public void submit(Orders orders) {
        Long userId = BaseContext.getCurrendId();

        // get user shopping cart
        LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getUserId, userId);
        List<ShoppingCart> shoppingCartList = shoppingCartService.list(shoppingCartLambdaQueryWrapper);

        if (shoppingCartList == null || shoppingCartList.size() == 0)
            throw new CustomException("Shopping cart is null, cannot checkout");

        // get user info
        User user = userService.getById(userId);

        // get address info
        Long addressBookId = orders.getAddressBookId();
        AddressBook addressBook = addressBookService.getById(addressBookId);
        if (addressBook == null) throw new CustomException("Address is null, cannot checkout");

        // save order
        long orderId = IdWorker.getId();
        AtomicInteger amount = new AtomicInteger(0);

        List<OrderDetail> orderDetails = shoppingCartList.stream().map(item -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setNumber(item.getNumber());
            orderDetail.setDishFlavor(item.getDishFlavor());
            orderDetail.setDishId(item.getDishId());
            orderDetail.setSetmealId(item.getSetmealId());
            orderDetail.setName(item.getName());
            orderDetail.setImage(item.getImage());
            orderDetail.setAmount(item.getAmount());
            amount.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());

        orders.setId(orderId);
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setStatus(2);
        orders.setAmount(new BigDecimal(amount.get()));
        orders.setUserId(userId);
        orders.setNumber(String.valueOf(orderId));
        orders.setUserName(user.getName());
        orders.setConsignee(addressBook.getConsignee());
        orders.setPhone(addressBook.getPhone());
        orders.setAddress((addressBook.getProvinceName() == null ? "" : addressBook.getProvinceName())
                + (addressBook.getCityName() == null ? "" : addressBook.getCityName())
                + (addressBook.getDistrictName() == null ? "" : addressBook.getDistrictName())
                + (addressBook.getDetail() == null ? "" : addressBook.getDetail()));
        this.save(orders);

        // save order detail
        orderDetailService.saveBatch(orderDetails);

        // clear cart
        shoppingCartService.remove(shoppingCartLambdaQueryWrapper);
    }

    @Override
    public Page<OrdersDto> getOrderPage(PageDto pageDto) {
        Page<Orders> ordersPage = new Page<>(pageDto.getPage(), pageDto.getPageSize());
        Page<OrdersDto> ordersDtoPage = new Page<>();

        LambdaQueryWrapper<Orders> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Orders::getUserId, BaseContext.getCurrendId());
        lambdaQueryWrapper.orderByDesc(Orders::getOrderTime);

        this.page(ordersPage, lambdaQueryWrapper);

        BeanUtils.copyProperties(ordersPage, ordersDtoPage, "records");

        List<Orders> records = ordersPage.getRecords();
        List<OrdersDto> list = records.stream().map(record -> {
            OrdersDto ordersDto = new OrdersDto();
            BeanUtils.copyProperties(record, ordersDto);

            List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrderId(ordersDto.getId());
            ordersDto.setOrderDetails(orderDetails);

            return ordersDto;
        }).collect(Collectors.toList());

        ordersDtoPage.setRecords(list);

        return ordersDtoPage;
    }

}
