package com.ezone.service;

import com.ezone.entity.CustomerPayment;
import com.ezone.form.create.CreatingCustomerPaymentForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingCustomerPaymentForm;
import com.ezone.repository.ICustomerPaymentRepository;
import com.ezone.specification.CustomerPaymentSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Service
public class CustomerPaymentService implements ICustomerPaymentService {
    @Autowired
    private ICustomerPaymentRepository customerPaymentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<CustomerPayment> getAllCustomerPayment(Pageable pageable, FilterForm form) {
        Specification<CustomerPayment> where = CustomerPaymentSpecification.buildWhere(form);
        return customerPaymentRepository.findAll(where, pageable);
    }

    @Override
    public void createNewCustomerPayment(CreatingCustomerPaymentForm form) {
        TypeMap<CreatingCustomerPaymentForm, CustomerPayment> typeMap = modelMapper.getTypeMap(CreatingCustomerPaymentForm.class, CustomerPayment.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingCustomerPaymentForm, CustomerPayment>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }

        CustomerPayment customerPayment = modelMapper.map(form, CustomerPayment.class);

        //Convert String to LocalDate:
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM");
        YearMonth yearMonth = YearMonth.parse(form.getCardExpired(), formatter);
        LocalDate date = LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), yearMonth.atEndOfMonth().getDayOfMonth());

        customerPayment.setCardExpired(date);

        customerPaymentRepository.save(customerPayment);
    }

    @Override
    public void updateCustomerPayment(UpdatingCustomerPaymentForm form) {
        CustomerPayment customerPayment = modelMapper.map(form, CustomerPayment.class);

        //Convert String to LocalDate:
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM");
        YearMonth yearMonth = YearMonth.parse(form.getCardExpired(), formatter);
        LocalDate date = LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), yearMonth.atEndOfMonth().getDayOfMonth());

        customerPayment.setCardExpired(date);

        customerPaymentRepository.save(customerPayment);
    }

    @Override
    public void deleteCustomerPaymentById(int paymentId) {
        customerPaymentRepository.deleteById(paymentId);
    }

    @Override
    public CustomerPayment findById(int id) {
        return customerPaymentRepository.findById(id).get();
    }
}
