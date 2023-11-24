package com.ezone.service;

import com.ezone.entity.Order;
import com.ezone.entity.OrderDetail;
import com.ezone.entity.Product;
import com.ezone.entity.ProductInventory;
import com.ezone.form.create.CreatingOrderDetailForm;
import com.ezone.form.create.CreatingOrderForm;
import com.ezone.repository.IOrderDetailRepository;
import com.ezone.repository.IProductInventoryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService implements IOrderDetailService {
    @Autowired
    private IOrderDetailRepository orderDetailRepository;
    @Autowired
    private IProductInventoryRepository productInventoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<OrderDetail> getAllOrderDetailByOrderId(int orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    @Override
    public void createNewOrderDetail(CreatingOrderDetailForm form) {
        TypeMap<CreatingOrderDetailForm, OrderDetail> typeMap = modelMapper.getTypeMap(CreatingOrderDetailForm.class, OrderDetail.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingOrderDetailForm, OrderDetail>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        OrderDetail orderDetail = modelMapper.map(form, OrderDetail.class);
        orderDetailRepository.save(orderDetail);

        //update product inventory quantity
        ProductInventory productInventory = productInventoryRepository.findById(form.getProductInventoryId()).get();
        productInventory.setStock(productInventory.getStock() - 1);
        productInventoryRepository.save(productInventory);
    }

    @Override
    public OrderDetail findById(int id) {
        return orderDetailRepository.findById(id).get();
    }
}
