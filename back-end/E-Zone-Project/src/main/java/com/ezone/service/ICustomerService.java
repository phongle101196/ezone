package com.ezone.service;

import com.ezone.entity.Customer;
import com.ezone.form.create.CreatingCustomerForm;
import com.ezone.form.filter.FilterForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService {
    Page<Customer> getAllCustomer(Pageable pageable, FilterForm form);

    Customer getCustomerById (int customerId);

    void createNewCustomer(CreatingCustomerForm form);

    void deleteCustomerById(int customerId);

    Customer getCustomerByUserId (int userId);
}
