package com.ezone.service;

import com.ezone.entity.ProductInventorySale;
import com.ezone.form.create.CreatingProductInventorySaleForm;
import com.ezone.form.update.UpdatingProductInventorySaleForm;
import com.ezone.repository.IProductInventorySaleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInventorySaleService implements IProductInventorySaleService {
    @Autowired
    private IProductInventorySaleRepository productInventorySaleRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductInventorySale getProductInventorySaleById(int inventorySaleId) {
        return productInventorySaleRepository.findById(inventorySaleId).get();
    }

    @Override
    public ProductInventorySale getProductInventorySaleByProductInventoryId(int productInventoryId) {
        return productInventorySaleRepository.findByProductInventoryId(productInventoryId);
    }

    @Override
    public void createNewProductInventorySale(CreatingProductInventorySaleForm form) {
        TypeMap<CreatingProductInventorySaleForm, ProductInventorySale> typeMap = modelMapper.getTypeMap(CreatingProductInventorySaleForm.class, ProductInventorySale.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingProductInventorySaleForm, ProductInventorySale>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }

        ProductInventorySale productInventorySale = modelMapper.map(form, ProductInventorySale.class);
        productInventorySaleRepository.save(productInventorySale);
    }

    @Override
    public void updateProductInventorySale(UpdatingProductInventorySaleForm form) {
        ProductInventorySale productInventorySale = productInventorySaleRepository.findById(form.getId()).get();
        productInventorySale.setSalePrice(form.getSalePrice());
        productInventorySaleRepository.save(productInventorySale);
    }

    @Override
    public void deleteProductInventorySaleById(int inventorySaleId) {
        productInventorySaleRepository.deleteById(inventorySaleId);
    }

    @Override
    public Page<ProductInventorySale> findAll(Pageable pageable) {
        return productInventorySaleRepository.findAll(pageable);
    }
}
