package com.ezone.service;

import com.ezone.entity.Bank;
import com.ezone.form.create.CreatingBankForm;
import com.ezone.repository.IBankRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService implements IBankService {
    @Autowired
    private IBankRepository bankRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Bank> getAllBank() {
        return bankRepository.findAll();
    }

    @Override
    public Bank getBankByName(String name) {
        return bankRepository.findByName(name);
    }

    @Override
    public Bank addNewBank(CreatingBankForm form) {
        Bank bank = modelMapper.map(form, Bank.class);
        bankRepository.save(bank);
        return bankRepository.findByName(form.getName());
    }

    @Override
    public void deleteBankById(int bankId) {
        bankRepository.deleteById(bankId);
    }

    @Override
    public Bank findById(int id) {
        return bankRepository.findById(id).get();
    }
}
