package com.ezone.controller;

import com.ezone.dto.OrderDTO;
import com.ezone.entity.Order;
import com.ezone.form.create.CreatingOrderForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingOrderStatusForm;
import com.ezone.service.IOrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/orders")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<OrderDTO> getAllOrder(Pageable pageable, FilterForm form) {
        Page<Order> orderPage = orderService.getAllOrder(pageable, form);
        List<Order> orders = orderPage.getContent();
        List<OrderDTO> orderDTOS = modelMapper.map(orders, new TypeToken<List<OrderDTO>>() {
        }.getType());
        return new PageImpl<>(orderDTOS, pageable, orderPage.getTotalElements());
    }

    @PostMapping
    public OrderDTO createNewOrder(@RequestBody CreatingOrderForm form) {
        return modelMapper.map(orderService.createNewOrder(form), OrderDTO.class);
    }

    @DeleteMapping(value = "/{orderId}")
    public void deleteOrderById(@PathVariable(name = "orderId") int orderId) {
        orderService.deleteOrderById(orderId);
    }

    @GetMapping(value = "/{id}")
    public OrderDTO findById(@PathVariable(name = "id") int id) {
        return modelMapper.map(orderService.findById(id), OrderDTO.class);
    }

    @PutMapping(value = "/update")
    public void updateOrderStatus(@RequestBody UpdatingOrderStatusForm form) {
        orderService.updateOrderStatus(form);
    }
}
