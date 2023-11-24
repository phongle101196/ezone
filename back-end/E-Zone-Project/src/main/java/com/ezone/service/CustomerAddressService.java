package com.ezone.service;

import com.ezone.entity.CustomerAddress;
import com.ezone.form.create.CreatingCustomerAddressForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingCustomerAddressForm;
import com.ezone.repository.ICustomerAddressRepository;
import com.ezone.specification.CustomerAddressSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CustomerAddressService implements ICustomerAddressService {
    @Autowired
    private ICustomerAddressRepository customerAddressRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<CustomerAddress> getAllCustomerAddress(Pageable pageable, FilterForm form) {
        Specification<CustomerAddress> where = CustomerAddressSpecification.buildWhere(form);
        return customerAddressRepository.findAll(where, pageable);
    }


    @Override
    public void createNewCustomerAddress(CreatingCustomerAddressForm form) {
        TypeMap<CreatingCustomerAddressForm, CustomerAddress> typeMap = modelMapper.getTypeMap(CreatingCustomerAddressForm.class, CustomerAddress.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingCustomerAddressForm, CustomerAddress>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }

        CustomerAddress customerAddress = modelMapper.map(form, CustomerAddress.class);
        customerAddressRepository.save(customerAddress);
    }

    @Override
    public void updateCustomerAddress(UpdatingCustomerAddressForm form) {
        CustomerAddress tmp = customerAddressRepository.findById(form.getId()).get();
        CustomerAddress customerAddress = modelMapper.map(form, CustomerAddress.class);
        customerAddress.setCustomer(tmp.getCustomer());
        customerAddressRepository.save(customerAddress);
    }

    @Override
    public void deleteCustomerAddressById(int addressId) {
        customerAddressRepository.deleteById(addressId);
    }

    @Override
    public CustomerAddress findById(int id) {
        return customerAddressRepository.findById(id).get();
    }

}
