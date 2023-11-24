package com.ezone.service;

import com.ezone.entity.ProductRating;
import com.ezone.form.create.CreatingProductRatingForm;
import com.ezone.form.update.UpdatingProductRatingForm;

import java.util.List;

public interface IProductRatingService {
    List<ProductRating> getAllProductRatingByProductId(int productId);

    ProductRating getProductRatingById(int ratingId);

    void createNewProductRating(CreatingProductRatingForm form);

    void updateProductRating(UpdatingProductRatingForm form);

    void deleteProductRatingById(int ratingId);

    void confirmProductRating(int ratingId);
}
