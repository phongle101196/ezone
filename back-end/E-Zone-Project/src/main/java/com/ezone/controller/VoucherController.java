package com.ezone.controller;

import com.ezone.dto.VoucherDTO;
import com.ezone.entity.Voucher;
import com.ezone.form.create.CreatingVoucherForm;
import com.ezone.form.update.UpdatingVoucherForm;
import com.ezone.service.IVoucherService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/vouchers")
@CrossOrigin("*")
public class VoucherController {
    @Autowired
    private IVoucherService voucherService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<VoucherDTO> getAllVoucher(Pageable pageable) {
        Page<Voucher> voucherPage = voucherService.getAllVoucher(pageable);
        List<Voucher> vouchers = voucherPage.getContent();
        List<VoucherDTO> voucherDTOS = modelMapper.map(vouchers, new TypeToken<List<VoucherDTO>>() {
        }.getType());
        return new PageImpl<>(voucherDTOS, pageable, voucherPage.getTotalElements());
    }

    @GetMapping(value = "/code/{code}")
    public VoucherDTO getVoucherByVoucherCode(@PathVariable(name = "code") String code) {
        return modelMapper.map(voucherService.getVoucherByVoucherCode(code), VoucherDTO.class);
    }

    @PostMapping
    public void createNewVoucher(@RequestBody CreatingVoucherForm form) {
        voucherService.createNewVoucher(form);
    }

    @PutMapping(value = "/{voucherId}")
    public void updateVoucher(@PathVariable(name = "voucherId") int voucherId, @RequestBody UpdatingVoucherForm form) {
        form.setId(voucherId);
        voucherService.updateVoucher(form);
    }

    @DeleteMapping(value = "/{voucherId}")
    public void deleteVoucherById(@PathVariable(name = "voucherId") int voucherId) {
        voucherService.deleteVoucherById(voucherId);
    }
}
