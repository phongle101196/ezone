package com.ezone.controller;

import com.ezone.dto.CustomerPaymentDTO;
import com.ezone.entity.CustomerPayment;
import com.ezone.form.create.CreatingCustomerPaymentForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingCustomerPaymentForm;
import com.ezone.service.ICustomerPaymentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/customerPayments")
@CrossOrigin("*")
public class CustomerPaymentController {
    @Autowired
    private ICustomerPaymentService customerPaymentService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<CustomerPaymentDTO> getAllCustomerPayment(Pageable pageable, FilterForm form) {
        Page<CustomerPayment> customerPaymentPage = customerPaymentService.getAllCustomerPayment(pageable, form);
        List<CustomerPayment> customerPayments = customerPaymentPage.getContent();
        List<CustomerPaymentDTO> customerPaymentDTOS = modelMapper.map(customerPayments, new TypeToken<List<CustomerPaymentDTO>>() {
        }.getType());
        return new PageImpl<>(customerPaymentDTOS, pageable, customerPaymentPage.getTotalElements());
    }

    @PostMapping
    public void createNewCustomerPayment(@RequestBody @Valid CreatingCustomerPaymentForm form) {
        customerPaymentService.createNewCustomerPayment(form);
    }

    @PutMapping(value = "/{paymentId}")
    public void updateCustomerPayment(@PathVariable(name = "paymentId") int paymentId, @RequestBody @Valid UpdatingCustomerPaymentForm form) {
        form.setId(paymentId);
        customerPaymentService.updateCustomerPayment(form);
    }

    @DeleteMapping(value = "/{paymentId}")
    public void deleteCustomerPaymentById(@PathVariable(name = "paymentId") int paymentId) {
        customerPaymentService.deleteCustomerPaymentById(paymentId);
    }

    @GetMapping(value = "/{id}")
    public CustomerPayment findById(@PathVariable(name = "id") int id) {
        return customerPaymentService.findById(id);
    }
}
