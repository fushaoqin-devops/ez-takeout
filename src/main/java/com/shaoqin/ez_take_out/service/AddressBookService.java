package com.shaoqin.ez_take_out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shaoqin.ez_take_out.entity.AddressBook;

import java.util.List;

/**
 * ClassName: AddressBookService
 * Package: com.shaoqin.ez_take_out.service
 * Description:
 * Author Shaoqin
 * Create 6/29/23 12:23 PM
 * Version 1.0
 */
public interface AddressBookService extends IService<AddressBook> {

    public AddressBook saveAddress(AddressBook addressBook);

    AddressBook setDefault(AddressBook addressBook);

    AddressBook getDefault();

    AddressBook getAddressBy(Long id);

    List<AddressBook> getList(AddressBook addressBook);

}
