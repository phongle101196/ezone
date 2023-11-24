package com.ezone.service;

import com.ezone.entity.Product;
import com.ezone.form.create.CreatingProductForm;
import com.ezone.form.filter.ProductFilterForm;
import com.ezone.form.update.UpdatingProductForm;
import com.ezone.repository.IProductRepository;
import com.ezone.specification.ProductSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<Product> getAllProduct(Pageable pageable, ProductFilterForm form) {
        Specification<Product> where = ProductSpecification.buildWhere(form);
        return productRepository.findAll(where, pageable);
    }

    @Override
    public Product getProductById(int productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public void createNewProduct(CreatingProductForm form) {
        TypeMap<CreatingProductForm, Product> typeMap = modelMapper.getTypeMap(CreatingProductForm.class, Product.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingProductForm, Product>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        Product product = modelMapper.map(form, Product.class);
        productRepository.save(product);
    }

    @Override
    public void updateProduct(UpdatingProductForm form) {
        Product tmp = productRepository.findById(form.getId()).get();

        Product product = modelMapper.map(form, Product.class);
        product.setCreatedDate(tmp.getCreatedDate());
        product.setProductInventories(tmp.getProductInventories());
        product.setProductComments(tmp.getProductComments());
        product.setProductRatings(tmp.getProductRatings());
        product.setProductVouchers(tmp.getProductVouchers());

        productRepository.save(product);
    }

    @Override
    public void deleteProductById(int productId) {
        productRepository.deleteById(productId);
    }
}
