package com.ezone.service;

import com.ezone.entity.ProductRating;
import com.ezone.form.create.CreatingProductRatingForm;
import com.ezone.form.update.UpdatingProductRatingForm;
import com.ezone.repository.IProductRatingRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRatingService implements IProductRatingService {
    @Autowired
    private IProductRatingRepository productRatingRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductRating> getAllProductRatingByProductId(int productId) {
        return productRatingRepository.findByProductId(productId);
    }

    @Override
    public ProductRating getProductRatingById(int ratingId) {
        return productRatingRepository.findById(ratingId).get();
    }

    @Override
    public void createNewProductRating(CreatingProductRatingForm form) {
        TypeMap<CreatingProductRatingForm, ProductRating> typeMap = modelMapper.getTypeMap(CreatingProductRatingForm.class, ProductRating.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingProductRatingForm, ProductRating>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        ProductRating productRating = modelMapper.map(form, ProductRating.class);
        productRatingRepository.save(productRating);
    }

    @Override
    public void updateProductRating(UpdatingProductRatingForm form) {
        ProductRating productRating = productRatingRepository.findById(form.getId()).get();
        productRating.setRating(form.getRating());
        productRatingRepository.save(productRating);
    }

    @Override
    public void deleteProductRatingById(int ratingId) {
        productRatingRepository.deleteById(ratingId);
    }

    @Override
    public void confirmProductRating(int ratingId) {
        ProductRating productRating = productRatingRepository.findById(ratingId).get();
        productRating.setConfirmed(true);
        productRatingRepository.save(productRating);
    }
}
