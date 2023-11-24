package com.ezone.controller;

import com.ezone.dto.CustomerDTO;
import com.ezone.entity.Customer;
import com.ezone.form.create.CreatingCustomerForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.service.ICustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/customers")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<CustomerDTO> getAllCustomer(Pageable pageable, FilterForm form) {
        Page<Customer> customerPage = customerService.getAllCustomer(pageable, form);
        List<Customer> customers = customerPage.getContent();
        List<CustomerDTO> customerDTOS = modelMapper.map(customers, new TypeToken<List<CustomerDTO>>() {
        }.getType());
        return new PageImpl<>(customerDTOS, pageable, customerPage.getTotalElements());
    }

    @GetMapping(value = "/{customerId}")
    public CustomerDTO getCustomerById(@PathVariable(name = "customerId") int customerId) {
        return modelMapper.map(customerService.getCustomerById(customerId), CustomerDTO.class);
    }

    @PostMapping
    public void createNewCustomer(@RequestBody CreatingCustomerForm form) {
        customerService.createNewCustomer(form);
    }

    @DeleteMapping(value = "/{customerId}")
    public void deleteCustomerById(@PathVariable(name = "customerId") int customerId) {
        customerService.deleteCustomerById(customerId);
    }

    @GetMapping(value = "/user/{userId}")
    public CustomerDTO getCustomerByUserId(@PathVariable(name = "userId") int userId) {
        return modelMapper.map(customerService.getCustomerByUserId(userId), CustomerDTO.class);
    }
}
