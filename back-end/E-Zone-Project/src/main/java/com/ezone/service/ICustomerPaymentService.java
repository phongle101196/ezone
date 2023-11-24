package com.ezone.service;

import com.ezone.entity.CustomerPayment;
import com.ezone.form.create.CreatingCustomerPaymentForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingCustomerPaymentForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerPaymentService {

    Page<CustomerPayment> getAllCustomerPayment(Pageable pageable, FilterForm form);

    void createNewCustomerPayment(CreatingCustomerPaymentForm form);

    void updateCustomerPayment(UpdatingCustomerPaymentForm form);

    void deleteCustomerPaymentById(int paymentId);

    CustomerPayment findById(int id);
}
