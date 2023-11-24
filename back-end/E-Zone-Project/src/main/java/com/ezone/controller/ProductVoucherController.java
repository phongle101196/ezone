package com.ezone.controller;

import com.ezone.dto.ProductVoucherDTO;
import com.ezone.entity.ProductVoucher;
import com.ezone.form.create.CreatingProductVoucherForm;
import com.ezone.service.IProductVoucherService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/productVouchers")
@CrossOrigin("*")
public class ProductVoucherController {
    @Autowired
    private IProductVoucherService productVoucherService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/voucher/{voucherId}")
    public List<ProductVoucherDTO> getProductVoucherByVoucherId(@PathVariable(name = "voucherId") int voucherId) {
        List<ProductVoucher> productVouchers = productVoucherService.getProductVoucherByVoucherId(voucherId);
        return modelMapper.map(productVouchers, new TypeToken<List<ProductVoucherDTO>>() {
        }.getType());
    }

    @GetMapping(value = "/product/{productId}")
    public List<ProductVoucherDTO> getProductVoucherByProductId(@PathVariable(name = "productId") int productId) {
        List<ProductVoucher> productVouchers = productVoucherService.getProductVoucherByProductId(productId);
        return modelMapper.map(productVouchers, new TypeToken<List<ProductVoucherDTO>>() {
        }.getType());
    }

    @PostMapping
    public void createNewProductVoucher(@RequestBody CreatingProductVoucherForm form) {
        productVoucherService.createNewProductVoucher(form);
    }

    @DeleteMapping(value = "/{productVoucherId}")
    public void deleteProductVoucherById(@PathVariable(name = "productVoucherId") int productVoucherId) {
        productVoucherService.deleteProductVoucherById(productVoucherId);
    }

}
