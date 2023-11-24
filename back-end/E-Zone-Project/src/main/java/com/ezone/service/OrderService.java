package com.ezone.service;

import com.ezone.entity.Customer;
import com.ezone.entity.Order;
import com.ezone.entity.OrderStatus;
import com.ezone.form.create.CreatingOrderForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingOrderStatusForm;
import com.ezone.repository.ICustomerRepository;
import com.ezone.repository.IOrderRepository;
import com.ezone.specification.OrderSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    ICustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<Order> getAllOrder(Pageable pageable, FilterForm form) {
        Specification<Order> where = OrderSpecification.buildWhere(form);
        return orderRepository.findAll(where, pageable);
    }

    @Override
    public Order createNewOrder(CreatingOrderForm form) {
        TypeMap<CreatingOrderForm, Order> typeMap = modelMapper.getTypeMap(CreatingOrderForm.class, Order.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingOrderForm, Order>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        Order order = modelMapper.map(form, Order.class);
        Order lastOrder = orderRepository.findTopByOrderByIdDesc();
        if (lastOrder == null) {
            order.setId(1);
        } else {
            order.setId(lastOrder.getId() + 1);
        }

        orderRepository.save(order);
        return orderRepository.findById(order.getId()).get();
    }

    @Override
    public void deleteOrderById(int orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order findById(int id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public void updateOrderStatus(UpdatingOrderStatusForm form) {
        Order order = orderRepository.findById(form.getOrderId()).get();
        order.setStatus(OrderStatus.valueOf(form.getStatus()));
        orderRepository.save(order);
    }
}
