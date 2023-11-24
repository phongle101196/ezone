package com.ezone.service;

import com.ezone.entity.ProductInventory;
import com.ezone.form.create.CreatingProductInventoryForm;
import com.ezone.form.filter.ProductFilterForm;
import com.ezone.form.update.UpdatingProductForm;
import com.ezone.form.update.UpdatingProductInventoryForm;
import com.ezone.repository.IProductInventoryRepository;
import com.ezone.specification.ProductInventorySpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductInventoryService implements IProductInventoryService {
    @Autowired
    private IProductInventoryRepository productInventoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<ProductInventory> getAllProductInventory(Pageable pageable, ProductFilterForm form) {
        Specification<ProductInventory> where = ProductInventorySpecification.buildWhere(form);
        return productInventoryRepository.findAll(where, pageable);
    }

    @Override
    public ProductInventory getProductInventoryById(int inventoryId) {
        return productInventoryRepository.findById(inventoryId).get();
    }

    @Override
    public void createNewProductInventory(CreatingProductInventoryForm form) {
        TypeMap<CreatingProductInventoryForm, ProductInventory> typeMap = modelMapper.getTypeMap(CreatingProductInventoryForm.class, ProductInventory.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingProductInventoryForm, ProductInventory>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        ProductInventory productInventory = modelMapper.map(form, ProductInventory.class);
        productInventoryRepository.save(productInventory);
    }

    @Override
    public void updateProductInventory(UpdatingProductInventoryForm form) {
        ProductInventory productInventory = productInventoryRepository.findById(form.getId()).get();
        productInventory.setCost(form.getCost());
        productInventory.setPrice(form.getPrice());
        productInventory.setStock(form.getStock());
        productInventoryRepository.save(productInventory);
    }

    @Override
    public void deleteProductInventoryById(int inventoryId) {
        productInventoryRepository.deleteById(inventoryId);
    }
}
