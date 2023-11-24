package com.ezone.controller;

import com.ezone.dto.CustomerAddressDTO;
import com.ezone.entity.CustomerAddress;
import com.ezone.form.create.CreatingCustomerAddressForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingCustomerAddressForm;
import com.ezone.service.ICustomerAddressService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/customerAddresses")
@CrossOrigin("*")
public class CustomerAddressController {
    @Autowired
    private ICustomerAddressService customerAddressService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<CustomerAddressDTO> getAllCustomerAddress(Pageable pageable, FilterForm form) {
        Page<CustomerAddress> customerAddressPage = customerAddressService.getAllCustomerAddress(pageable, form);
        List<CustomerAddress> customerAddresses = customerAddressPage.getContent();
        List<CustomerAddressDTO> customerAddressDTOS = modelMapper.map(customerAddresses, new TypeToken<List<CustomerAddressDTO>>() {
        }.getType());
        return new PageImpl<>(customerAddressDTOS, pageable, customerAddresses.size());
    }


    @PostMapping
    public void createNewCustomerAddress(@RequestBody CreatingCustomerAddressForm form) {
        customerAddressService.createNewCustomerAddress(form);
    }

    @PutMapping(value = "/{addressId}")
    public void updateCustomerAddress(@PathVariable(name = "addressId") int addressId, @RequestBody UpdatingCustomerAddressForm form) {
        form.setId(addressId);
        customerAddressService.updateCustomerAddress(form);
    }

    @DeleteMapping(value = "/{addressId}")
    public void deleteCustomerAddressById(@PathVariable(name = "addressId") int addressId) {
        customerAddressService.deleteCustomerAddressById(addressId);
    }

    @GetMapping(value = "/{id}")
    public CustomerAddressDTO findById(@PathVariable(name = "id") int id) {
        return modelMapper.map(customerAddressService.findById(id), CustomerAddressDTO.class);
    }
}