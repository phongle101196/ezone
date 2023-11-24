package com.ezone.service;

import com.ezone.entity.ProductCommentLike;
import com.ezone.form.create.CreatingProductCommentLikeForm;
import com.ezone.repository.IProductCommentLikeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCommentLikeService implements IProductCommentLikeService {
    @Autowired
    private IProductCommentLikeRepository productCommentLikeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductCommentLike> getAllProductCommentLikeByCommentId(int commentId) {
        return productCommentLikeRepository.findByProductCommentId(commentId);
    }

    @Override
    public ProductCommentLike getProductCommentLikeById(int commentLikeId) {
        return productCommentLikeRepository.findById(commentLikeId).get();
    }

    @Override
    public ProductCommentLike getProductCommentLikeByCommentIdAndUserId(int commentId, int userId) {
        return productCommentLikeRepository.findByProductCommentIdAndUserId(commentId, userId);
    }

    @Override
    public void createMewProductCommentLike(CreatingProductCommentLikeForm form) {
        TypeMap<CreatingProductCommentLikeForm, ProductCommentLike> typeMap = modelMapper.getTypeMap(CreatingProductCommentLikeForm.class, ProductCommentLike.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingProductCommentLikeForm, ProductCommentLike>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        ProductCommentLike productCommentLike = modelMapper.map(form, ProductCommentLike.class);
        productCommentLikeRepository.save(productCommentLike);
    }

    @Override
    public void deleteProductCommentLikeById(int commentLikeId) {
        productCommentLikeRepository.deleteById(commentLikeId);
    }
}
