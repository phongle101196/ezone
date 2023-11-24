package com.ezone.service;

import com.ezone.entity.Voucher;
import com.ezone.form.create.CreatingVoucherForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingVoucherForm;
import com.ezone.repository.IVoucherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VoucherService implements IVoucherService {
    @Autowired
    private IVoucherRepository voucherRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<Voucher> getAllVoucher(Pageable pageable) {
        return voucherRepository.findAll(pageable);
    }

    @Override
    public Voucher getVoucherByVoucherCode(String code) {
        return voucherRepository.findByCode(code);
    }

    @Override
    public void createNewVoucher(CreatingVoucherForm form) {
        Voucher voucher = modelMapper.map(form, Voucher.class);
        voucherRepository.save(voucher);
    }

    @Override
    public void updateVoucher(UpdatingVoucherForm form) {
        Voucher voucher = voucherRepository.findById(form.getId()).get();
        voucher.setActivated(form.isActivated());
        voucher.setStock(form.getStock());
        voucher.setStartDate(form.getStartDate());
        voucher.setEndDate(form.getEndDate());
        voucher.setDiscountAmount(form.getDiscountAmount());
        voucherRepository.save(voucher);
    }

    @Override
    public void deleteVoucherById(int voucherId) {
        voucherRepository.deleteById(voucherId);
    }
}
