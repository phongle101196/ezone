package com.ezone.service;

import com.ezone.entity.ProductVoucher;
import com.ezone.form.create.CreatingProductVoucherForm;
import com.ezone.repository.IProductVoucherRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductVoucherService implements IProductVoucherService {
    @Autowired
    private IProductVoucherRepository productVoucherRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductVoucher> getProductVoucherByVoucherId(int voucherId) {
        return productVoucherRepository.findByVoucherId(voucherId);
    }

    @Override
    public List<ProductVoucher> getProductVoucherByProductId(int productId) {
        return productVoucherRepository.findByProductId(productId);
    }

    @Override
    public void createNewProductVoucher(CreatingProductVoucherForm form) {
        TypeMap<CreatingProductVoucherForm, ProductVoucher> typeMap = modelMapper.getTypeMap(CreatingProductVoucherForm.class, ProductVoucher.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingProductVoucherForm, ProductVoucher>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        ProductVoucher productVoucher = modelMapper.map(form, ProductVoucher.class);
        productVoucherRepository.save(productVoucher);
    }

    @Override
    public void deleteProductVoucherById(int productVoucherId) {
        productVoucherRepository.deleteById(productVoucherId);
    }
}
