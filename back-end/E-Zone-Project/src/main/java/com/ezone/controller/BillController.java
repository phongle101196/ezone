package com.ezone.controller;

import com.ezone.dto.BillDTO;
import com.ezone.entity.Bill;
import com.ezone.form.create.CreatingBillForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.service.IBillService;
import net.bytebuddy.description.method.MethodDescription;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/bills")
@CrossOrigin("*")
public class BillController {
    @Autowired
    private IBillService billService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<BillDTO> getAllBill(Pageable pageable, FilterForm form) {
        Page<Bill> billPage = billService.getAllBill(pageable, form);
        List<Bill> bills = billPage.getContent();
        List<BillDTO> billDTOS = modelMapper.map(bills, new TypeToken<List<BillDTO>>() {
        }.getType());
        return new PageImpl<>(billDTOS, pageable, billPage.getTotalElements());
    }

    @PostMapping
    public void createNewBill(@RequestBody CreatingBillForm form) {
        billService.createNewBill(form);
    }

    @DeleteMapping(value = "/{billId}")
    public void deleteBillById(@PathVariable(name = "billId") int billId) {
        billService.deleteBillById(billId);
    }

    @GetMapping(value = "/{id}")
    public BillDTO findById(@PathVariable(name = "id") int id) {
        return modelMapper.map(billService.findById(id), BillDTO.class);
    }
}
