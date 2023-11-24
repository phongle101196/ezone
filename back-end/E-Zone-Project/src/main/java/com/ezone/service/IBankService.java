package com.ezone.service;

import com.ezone.entity.Bank;
import com.ezone.form.create.CreatingBankForm;

import java.util.List;

public interface IBankService {
    List<Bank> getAllBank();

    Bank getBankByName(String name);

    Bank addNewBank(CreatingBankForm form);

    void deleteBankById(int bankId);

    Bank findById(int id);
}
