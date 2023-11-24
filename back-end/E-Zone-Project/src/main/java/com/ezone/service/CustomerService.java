package com.ezone.service;

import com.ezone.entity.Customer;
import com.ezone.form.create.CreatingCustomerForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.repository.ICustomerRepository;
import com.ezone.specification.CustomerSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<Customer> getAllCustomer(Pageable pageable, FilterForm form) {
        Specification<Customer> where = CustomerSpecification.buildWhere(form);
        return customerRepository.findAll(where, pageable);
    }

    @Override
    public Customer getCustomerById(int customerId) {
        return customerRepository.findById(customerId).get();
    }

    @Override
    public void createNewCustomer(CreatingCustomerForm form) {
        TypeMap<CreatingCustomerForm, Customer> typeMap = modelMapper.getTypeMap(CreatingCustomerForm.class, Customer.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingCustomerForm, Customer>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        Customer customer = modelMapper.map(form, Customer.class);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(int customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer getCustomerByUserId(int userId) {
        return customerRepository.findByUserId(userId);
    }
}
