package com.ezone.controller;

import com.ezone.dto.ProductInventorySaleDTO;
import com.ezone.entity.ProductInventorySale;
import com.ezone.form.create.CreatingProductInventorySaleForm;
import com.ezone.form.update.UpdatingProductInventorySaleForm;
import com.ezone.service.IProductInventorySaleService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/productInventorySales")
@CrossOrigin("*")
public class ProductInventorySaleController {
    @Autowired
    private IProductInventorySaleService productInventorySaleService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/{inventorySaleId}")
    public ProductInventorySaleDTO getProductInventorySaleById(@PathVariable(name = "inventorySaleId") int inventorySaleId) {
        return modelMapper.map(productInventorySaleService.getProductInventorySaleById(inventorySaleId), ProductInventorySaleDTO.class);
    }

    @GetMapping(value = "/invent/{productInventoryId}")
    public ProductInventorySaleDTO getProductInventorySaleByProductInventoryId(@PathVariable(name = "productInventoryId") int productInventoryId) {
        return modelMapper.map(productInventorySaleService.getProductInventorySaleByProductInventoryId(productInventoryId), ProductInventorySaleDTO.class);
    }

    @PostMapping
    public void createNewProductInventorySale(@RequestBody CreatingProductInventorySaleForm form) {
        productInventorySaleService.createNewProductInventorySale(form);
    }

    @PutMapping(value = "/{inventorySaleId}")
    public void updateProductInventorySale(@PathVariable(name = "inventorySaleId") int inventorySaleId, @RequestBody UpdatingProductInventorySaleForm form) {
        form.setId(inventorySaleId);
        productInventorySaleService.updateProductInventorySale(form);
    }

    @DeleteMapping(value = "/{inventorySaleId}")
    public void deleteProductInventorySaleById(@PathVariable(name = "inventorySaleId") int inventorySaleId) {
        productInventorySaleService.deleteProductInventorySaleById(inventorySaleId);
    }

    @GetMapping
    public Page<ProductInventorySaleDTO> findAll(Pageable pageable) {
        Page<ProductInventorySale> productInventorySalePage = productInventorySaleService.findAll(pageable);
        List<ProductInventorySale> productInventorySales = productInventorySalePage.getContent();
        List<ProductInventorySaleDTO> productInventorySaleDTOS = modelMapper.map(productInventorySales, new TypeToken<List<ProductInventorySaleDTO>>() {
        }.getType());
        return new PageImpl<>(productInventorySaleDTOS, pageable, productInventorySalePage.getTotalElements());
    }
}
