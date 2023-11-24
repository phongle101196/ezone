package com.ezone.service;

import com.ezone.entity.*;
import com.ezone.form.create.CreatingOrderVoucherForm;
import com.ezone.repository.IOrderDetailRepository;
import com.ezone.repository.IOrderVoucherRepository;
import com.ezone.repository.IProductInventoryRepository;
import com.ezone.repository.IVoucherRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderVoucherService implements IOrderVoucherService {
    @Autowired
    private IOrderVoucherRepository orderVoucherRepository;
    @Autowired
    private IVoucherRepository voucherRepository;
    @Autowired
    private IOrderDetailRepository orderDetailRepository;
    @Autowired
    private IProductInventoryRepository productInventoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderVoucher getOrderVoucherByOrderId(int orderId) {
        return orderVoucherRepository.findByOrderId(orderId);
    }

//    @Override
//    public void createNewOrderVoucher(CreatingOrderVoucherForm form) {
//        //Get orderDetail list
//        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(form.getOrderId());
//        //Get productInventoryId list
//        List<Integer> productInventoryIds = new ArrayList<>();
//        orderDetails.forEach(orderDetail -> {
//            productInventoryIds.add(orderDetail.getProductInventory().getId());
//        });
//        //Check
//        boolean isCheck = checkVoucher(form.getCode(), productInventoryIds);
//
//        if (isCheck) {
//            TypeMap<CreatingOrderVoucherForm, OrderVoucher> typeMap = modelMapper.getTypeMap(CreatingOrderVoucherForm.class, OrderVoucher.class);
//            if (typeMap == null) {
//                modelMapper.addMappings(new PropertyMap<CreatingOrderVoucherForm, OrderVoucher>() {
//                    @Override
//                    protected void configure() {
//                        skip(destination.getId());
//                    }
//                });
//            }
//            OrderVoucher orderVoucher = modelMapper.map(form, OrderVoucher.class);
//            orderVoucherRepository.save(orderVoucher);
//
//            //Update voucher claimed
//            Voucher voucher = voucherRepository.findByCode(form.getCode());
//            voucher.setClaimed(voucher.getClaimed() + 1);
//            voucherRepository.save(voucher);
//        }
//    }


    @Override
    public void createNewOrderVoucher(CreatingOrderVoucherForm form) {

        TypeMap<CreatingOrderVoucherForm, OrderVoucher> typeMap = modelMapper.getTypeMap(CreatingOrderVoucherForm.class, OrderVoucher.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingOrderVoucherForm, OrderVoucher>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }

        OrderVoucher orderVoucher = modelMapper.map(form, OrderVoucher.class);

        Voucher voucher = voucherRepository.findByCode(form.getCode());
        orderVoucher.setVoucher(voucher);
        orderVoucherRepository.save(orderVoucher);

        //Update voucher claimed

        voucher.setClaimed(voucher.getClaimed() + 1);
        voucherRepository.save(voucher);

    }


    @Override
    public boolean checkVoucher(String code, List<Integer> productInventoryIds) {
        boolean isAvailable = false;
        //Get voucher
        Voucher voucher = voucherRepository.findByCode(code);
        if (voucher == null || !voucher.isActivated() || voucher.getClaimed() >= voucher.getStock() || voucher.getStartDate().isAfter(LocalDateTime.now()) || voucher.getEndDate().isBefore(LocalDateTime.now())) {
            return isAvailable;
        } else {
            //Get available product id list
            List<ProductVoucher> productVouchers = voucher.getProductVouchers();
            List<Integer> availableProductIds = new ArrayList<>();
            productVouchers.forEach(productVoucher -> {
                availableProductIds.add(productVoucher.getProduct().getId());
            });
            //Get product id list wanna check
            List<ProductInventory> productInventories = productInventoryRepository.findAllById(productInventoryIds);
            List<Integer> checkProductIds = new ArrayList<>();
            productInventories.forEach(productInventory -> {
                checkProductIds.add(productInventory.getProduct().getId());
            });

            //Check
            for (Integer checkProduct : checkProductIds
            ) {
                for (Integer availableProduct : availableProductIds
                ) {
                    if (checkProduct.equals(availableProduct)) {
                        isAvailable = true;
                        break;
                    }
                }
            }
        }
        return isAvailable;
    }
}
