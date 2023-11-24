package com.ezone.service;

import com.ezone.entity.CustomerAddress;
import com.ezone.form.create.CreatingCustomerAddressForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingCustomerAddressForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerAddressService {
    Page<CustomerAddress> getAllCustomerAddress(Pageable pageable, FilterForm form);

    void createNewCustomerAddress(CreatingCustomerAddressForm form);

    void updateCustomerAddress(UpdatingCustomerAddressForm form);

    void deleteCustomerAddressById(int addressId);

    CustomerAddress findById(int id);
}
