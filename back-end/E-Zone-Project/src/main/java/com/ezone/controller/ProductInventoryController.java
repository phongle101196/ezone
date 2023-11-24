package com.ezone.controller;

import com.ezone.dto.ProductInventoryDTO;
import com.ezone.entity.ProductInventory;
import com.ezone.form.create.CreatingProductInventoryForm;
import com.ezone.form.filter.ProductFilterForm;
import com.ezone.form.update.UpdatingProductInventoryForm;
import com.ezone.service.IProductInventoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/productInventories")
@CrossOrigin("*")
public class ProductInventoryController {
    @Autowired
    private IProductInventoryService productInventoryService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<ProductInventoryDTO> getAllProductInventory(Pageable pageable, ProductFilterForm form) {
        Page<ProductInventory> productInventoryPage = productInventoryService.getAllProductInventory(pageable, form);
        List<ProductInventory> productInventories = productInventoryPage.getContent();
        List<ProductInventoryDTO> productInventoryDTOS = modelMapper.map(productInventories, new TypeToken<List<ProductInventoryDTO>>() {
        }.getType());
        return new PageImpl<>(productInventoryDTOS, pageable, productInventoryPage.getTotalElements());
    }

    @GetMapping(value = "/{inventoryId}")
    ProductInventoryDTO getProductInventoryById(@PathVariable(name = "inventoryId") int inventoryId) {
        ProductInventory productInventory = productInventoryService.getProductInventoryById(inventoryId);
        return modelMapper.map(productInventory, ProductInventoryDTO.class);
    }

    @PostMapping
    void createNewProductInventory(@RequestBody CreatingProductInventoryForm form) {
        productInventoryService.createNewProductInventory(form);
    }

    @PutMapping(value = "/{inventoryId}")
    void updateProductInventory(@PathVariable(name = "inventoryId") int inventoryId, @RequestBody UpdatingProductInventoryForm form) {
        form.setId(inventoryId);
        productInventoryService.updateProductInventory(form);
    }

    @DeleteMapping(value = "/{inventoryId}")
    void deleteProductInventoryById(@PathVariable(name = "inventoryId") int inventoryId) {
        productInventoryService.deleteProductInventoryById(inventoryId);
    }
}
