package com.shaoqin.ez_take_out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shaoqin.ez_take_out.common.BaseContext;
import com.shaoqin.ez_take_out.common.CustomException;
import com.shaoqin.ez_take_out.common.R;
import com.shaoqin.ez_take_out.entity.AddressBook;
import com.shaoqin.ez_take_out.mapper.AddressBookMapper;
import com.shaoqin.ez_take_out.service.AddressBookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: AddressBookServiceImpl
 * Package: com.shaoqin.ez_take_out.service.impl
 * Description:
 * Author Shaoqin
 * Create 6/29/23 12:23 PM
 * Version 1.0
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

    @Override
    public AddressBook saveAddress(AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrendId());
        this.save(addressBook);
        return addressBook;
    }

    @Override
    @Transactional
    public AddressBook setDefault(AddressBook addressBook) {
        LambdaUpdateWrapper<AddressBook> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(AddressBook::getUserId, BaseContext.getCurrendId())
                .set(AddressBook::getIsDefault, 0);
        this.update(lambdaUpdateWrapper);

        addressBook.setIsDefault(1);
        this.updateById(addressBook);

        return addressBook;
    }

    @Override
    public AddressBook getDefault() {
        LambdaQueryWrapper<AddressBook> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AddressBook::getUserId, BaseContext.getCurrendId())
                .eq(AddressBook::getIsDefault, 1);
        AddressBook addressBook = this.getOne(lambdaQueryWrapper);
        if (addressBook == null) throw new CustomException("Cannot find default address");
        return addressBook;
    }

    @Override
    public AddressBook getAddressBy(Long id) {
        AddressBook addressBook = this.getById(id);
        if (addressBook == null) throw new CustomException("Cannot find address");
        return addressBook;
    }

    @Override
    public List<AddressBook> getList(AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrendId());
        LambdaQueryWrapper<AddressBook> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(addressBook.getUserId() != null, AddressBook::getUserId, addressBook.getUserId())
                .orderByDesc(AddressBook::getUpdateTime);
        return this.list(lambdaQueryWrapper);
    }

}
