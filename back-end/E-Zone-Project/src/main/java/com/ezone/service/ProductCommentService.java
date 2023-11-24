package com.ezone.service;

import com.ezone.entity.OrderVoucher;
import com.ezone.entity.ProductComment;
import com.ezone.form.create.CreatingOrderVoucherForm;
import com.ezone.form.create.CreatingProductCommentForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingProductCommentForm;
import com.ezone.repository.IProductCommentRepository;
import com.ezone.specification.ProductCommentSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductCommentService implements IProductCommentService {
    @Autowired
    private IProductCommentRepository productCommentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<ProductComment> getAllProductComment(Pageable pageable, FilterForm form) {
        Specification<ProductComment> where = ProductCommentSpecification.buildWhere(form);
        return productCommentRepository.findAll(where, pageable);
    }

    @Override
    public ProductComment getProductCommentById(int commentId) {
        return productCommentRepository.findById(commentId).get();
    }

    @Override
    public void createNewProductComment(CreatingProductCommentForm form) {
        TypeMap<CreatingProductCommentForm, ProductComment> typeMap = modelMapper.getTypeMap(CreatingProductCommentForm.class, ProductComment.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingProductCommentForm, ProductComment>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        ProductComment productComment = modelMapper.map(form, ProductComment.class);
        productCommentRepository.save(productComment);
    }

    @Override
    public void updateProductComment(UpdatingProductCommentForm form) {
        ProductComment productComment = productCommentRepository.findById(form.getId()).get();
        productComment.setContent(form.getContent());
        productCommentRepository.save(productComment);
    }

    @Override
    public void deleteProductCommentById(int commentId) {
        productCommentRepository.deleteById(commentId);
    }

    @Override
    public void confirmProductComment(int commentId) {
        ProductComment productComment = productCommentRepository.findById(commentId).get();
        productComment.setConfirmed(true);
        productCommentRepository.save(productComment);
    }
}
