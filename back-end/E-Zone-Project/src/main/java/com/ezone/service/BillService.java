package com.ezone.service;

import com.ezone.entity.*;
import com.ezone.form.create.CreatingBillForm;
import com.ezone.form.create.CreatingConversationForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.repository.*;
import com.ezone.specification.BillSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService implements IBillService {
    @Autowired
    private IBillRepository billRepository;
    @Autowired
    private IOrderVoucherRepository orderVoucherRepository;
    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<Bill> getAllBill(Pageable pageable, FilterForm form) {
        Specification<Bill> where = BillSpecification.buildWhere(form);
        return billRepository.findAll(where, pageable);
    }

    @Override
    public void createNewBill(CreatingBillForm form) {
        //Get orderVoucher
        OrderVoucher orderVoucher = orderVoucherRepository.findByOrderId(form.getOrderId());
        //Get orderDetails
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(form.getOrderId());

        TypeMap<CreatingBillForm, Bill> typeMap = modelMapper.getTypeMap(CreatingBillForm.class, Bill.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingBillForm, Bill>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }

        //Create new bill
        Bill bill = modelMapper.map(form, Bill.class);

        int amount = 0;
        for (OrderDetail orderDetail :
                orderDetails) {
            amount = amount + orderDetail.getQuantity();
        }
        bill.setAmount(amount);

        if (orderVoucher != null) {
            bill.setDiscount(form.getDiscount() + orderVoucher.getVoucher().getDiscountAmount());
        }

        int total = 0;
        for (OrderDetail orderDetail : orderDetails
        ) {
            if (orderDetail.getProductInventory().getProductInventorySale() != null) {
                total = total + orderDetail.getQuantity() * orderDetail.getProductInventory().getProductInventorySale().getSalePrice();
            } else {
                total = total + orderDetail.getQuantity() * orderDetail.getProductInventory().getPrice();
            }
        }
        total = total - bill.getDiscount();

        if (total > 0) {
            bill.setTotal(total);
        } else {
            bill.setTotal(0);
        }

        billRepository.save(bill);
    }

    @Override
    public void deleteBillById(int billId) {
        billRepository.deleteById(billId);
    }

    @Override
    public Bill findById(int id) {
        return billRepository.findById(id).get();
    }
}
