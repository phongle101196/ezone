package com.ezone.service;

import com.ezone.entity.Product;
import com.ezone.entity.ProductSubComment;
import com.ezone.form.create.CreatingProductForm;
import com.ezone.form.create.CreatingProductSubCommentForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingProductSubCommentForm;
import com.ezone.repository.IProductSubCommentRepository;
import com.ezone.specification.ProductSubCommentSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductSubCommentService implements IProductSubCommentService {
    @Autowired
    private IProductSubCommentRepository productSubCommentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<ProductSubComment> getAllProductSubComment(Pageable pageable, FilterForm form) {
        Specification<ProductSubComment> where = ProductSubCommentSpecification.buildWhere(form);
        return productSubCommentRepository.findAll(where, pageable);
    }

    @Override
    public ProductSubComment getProductSubCommentById(int subCommentId) {
        return productSubCommentRepository.findById(subCommentId).get();
    }

    @Override
    public void createNewProductSubComment(CreatingProductSubCommentForm form) {
        TypeMap<CreatingProductSubCommentForm, ProductSubComment> typeMap = modelMapper.getTypeMap(CreatingProductSubCommentForm.class, ProductSubComment.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingProductSubCommentForm, ProductSubComment>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        ProductSubComment productSubComment = modelMapper.map(form, ProductSubComment.class);
        productSubCommentRepository.save(productSubComment);
    }

    @Override
    public void updateProductSubComment(UpdatingProductSubCommentForm form) {
        ProductSubComment productSubComment = productSubCommentRepository.findById(form.getId()).get();
        productSubComment.setContent(form.getContent());
        productSubCommentRepository.save(productSubComment);
    }

    @Override
    public void deleteProductSubCommentById(int subCommentId) {
        productSubCommentRepository.deleteById(subCommentId);
    }
}
