package com.ezone.controller;

import com.ezone.dto.ProductDTO;
import com.ezone.entity.Product;
import com.ezone.form.create.CreatingProductForm;
import com.ezone.form.filter.ProductFilterForm;
import com.ezone.form.update.UpdatingProductForm;
import com.ezone.service.IProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/products")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<ProductDTO> getAllProduct(Pageable pageable, ProductFilterForm form) {
        Page<Product> productPage = productService.getAllProduct(pageable, form);
        List<Product> products = productPage.getContent();
        List<ProductDTO> productDTOS = modelMapper.map(products, new TypeToken<List<ProductDTO>>() {
        }.getType());
        return new PageImpl<>(productDTOS, pageable, productPage.getTotalElements());
    }

    @GetMapping(value = "/{productId}")
    public ProductDTO getProductById(@PathVariable(name = "productId") int productId) {
        return modelMapper.map(productService.getProductById(productId), ProductDTO.class);
    }

    @PostMapping
    public void createNewProduct(@RequestBody CreatingProductForm form) {
        productService.createNewProduct(form);
    }

    @PutMapping(value = "/{productId}")
    public void updateProduct(@PathVariable(name = "productId") int productId, @RequestBody UpdatingProductForm form) {
        form.setId(productId);
        productService.updateProduct(form);
    }

    @DeleteMapping(value = "/{productId}")
    public void deleteProductById(@PathVariable(name = "productId") int productId) {
        productService.deleteProductById(productId);
    }
}
