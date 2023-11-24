package com.ezone.controller;

import com.ezone.dto.OrderVoucherDTO;
import com.ezone.entity.OrderVoucher;
import com.ezone.form.check.CheckingOrderVoucherForm;
import com.ezone.form.create.CreatingOrderVoucherForm;
import com.ezone.service.IOrderVoucherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/orderVouchers")
@CrossOrigin("*")
public class OrderVoucherController {
    @Autowired
    private IOrderVoucherService orderVoucherService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/{orderId}")
    public OrderVoucherDTO getOrderVoucherByOrderId(@PathVariable(name = "orderId") int orderId) {
        return modelMapper.map(orderVoucherService.getOrderVoucherByOrderId(orderId), OrderVoucherDTO.class);
    }

    @PostMapping
    public void createNewOrderVoucher(@RequestBody CreatingOrderVoucherForm form) {
        orderVoucherService.createNewOrderVoucher(form);
    }

    @PostMapping(value = "/check")
    public boolean checkVoucher(@RequestBody CheckingOrderVoucherForm form) {
        return orderVoucherService.checkVoucher(form.getCode(),form.getProductInventoryIds());
    }

}
