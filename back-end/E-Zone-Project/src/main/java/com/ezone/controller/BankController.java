package com.ezone.controller;

import com.ezone.entity.Bank;
import com.ezone.form.create.CreatingBankForm;
import com.ezone.service.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/banks")
@CrossOrigin("*")
public class BankController {
    @Autowired
    private IBankService bankService;

    @GetMapping
    public List<Bank> getAllBank() {
        return bankService.getAllBank();
    }

    @PostMapping
    public Bank addNewBank(@RequestBody CreatingBankForm form) {
        bankService.addNewBank(form);
        return bankService.getBankByName(form.getName());
    }

    @DeleteMapping(value = "/{bankId}")
    public void deleteBankById(@PathVariable(name = "bankId") int bankId) {
        bankService.deleteBankById(bankId);
    }

    @GetMapping(value = "/{id}")
    public Bank findById(@PathVariable(name = "id") int id){
        return bankService.findById(id);
    }
}
