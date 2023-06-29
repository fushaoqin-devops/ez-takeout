package com.shaoqin.ez_take_out.controller;

import com.shaoqin.ez_take_out.common.R;
import com.shaoqin.ez_take_out.entity.AddressBook;
import com.shaoqin.ez_take_out.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: AddressBookController
 * Package: com.shaoqin.ez_take_out.controller
 * Description:
 * Author Shaoqin
 * Create 6/29/23 12:24 PM
 * Version 1.0
 */
@RestController
@RequestMapping("/addressBook")
@Slf4j
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @PostMapping
    public R<AddressBook> save(@RequestBody AddressBook addressBook) {
        AddressBook savedAddressBook = addressBookService.saveAddress(addressBook);
        return R.success(savedAddressBook);
    }

    @PutMapping("/default")
    public R<AddressBook> setDefault(@RequestBody AddressBook addressBook) {
        AddressBook defaultAddress = addressBookService.setDefault(addressBook);
        return R.success(defaultAddress);
    }

    @GetMapping("/{id}")
    public R<AddressBook> get(@PathVariable("id") Long id) {
        AddressBook addressBook = addressBookService.getAddressBy(id);
        return R.success(addressBook);
    }

    @GetMapping("/default")
    public R<AddressBook> getDefault() {
        AddressBook addressBook = addressBookService.getDefault();
        return R.success(addressBook);
    }

    @GetMapping("/list")
    public R<List<AddressBook>> list(AddressBook addressBook) {
        List<AddressBook> list = addressBookService.getList(addressBook);
        return R.success(list);
    }

}
