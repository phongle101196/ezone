package com.ezone.controller;

import com.ezone.dto.OrderDetailDTO;
import com.ezone.entity.OrderDetail;
import com.ezone.form.create.CreatingOrderDetailForm;
import com.ezone.service.IOrderDetailService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/orderDetails")
@CrossOrigin("*")
public class OrderDetailController {
    @Autowired
    private IOrderDetailService orderDetailService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<OrderDetailDTO> getAllOrderDetailByOrderId(@RequestParam int orderId) {
        List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetailByOrderId(orderId);
        return modelMapper.map(orderDetails, new TypeToken<List<OrderDetailDTO>>() {
        }.getType());
    }

    @PostMapping
    public void createNewOrderDetail(@RequestBody CreatingOrderDetailForm form) {
        orderDetailService.createNewOrderDetail(form);
    }

    @GetMapping(value = "/{id}")
    public OrderDetailDTO findById(@PathVariable(name = "id") int id) {
        return modelMapper.map(orderDetailService.findById(id), OrderDetailDTO.class);
    }
}
