package com.ezone.controller;

import com.ezone.dto.ProductRatingDTO;
import com.ezone.entity.ProductRating;
import com.ezone.form.create.CreatingProductRatingForm;
import com.ezone.form.update.UpdatingProductRatingForm;
import com.ezone.service.IProductRatingService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/productRatings")
@CrossOrigin("*")
public class ProductRatingController {
    @Autowired
    private IProductRatingService productRatingService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/product/{productId}")
    public List<ProductRatingDTO> getAllProductRatingByProductId(@PathVariable(name = "productId") int productId) {
        List<ProductRating> productRatings = productRatingService.getAllProductRatingByProductId(productId);
        return modelMapper.map(productRatings, new TypeToken<List<ProductRatingDTO>>() {
        }.getType());
    }

    @GetMapping(value = "/{ratingId}")
    public ProductRatingDTO getProductRatingById(@PathVariable(name = "ratingId") int ratingId) {
        return modelMapper.map(productRatingService.getProductRatingById(ratingId), ProductRatingDTO.class);
    }

    @PostMapping
    public void createNewProductRating(@RequestBody CreatingProductRatingForm form) {
        productRatingService.createNewProductRating(form);
    }

    @PutMapping(value = "/{ratingId}")
    public void updateProductRating(@PathVariable(name = "ratingId") int ratingId, @RequestBody UpdatingProductRatingForm form) {
        form.setId(ratingId);
        productRatingService.updateProductRating(form);
    }

    @DeleteMapping(value = "/{ratingId}")
    public void deleteProductRatingById(@PathVariable(name = "ratingId") int ratingId) {
        productRatingService.deleteProductRatingById(ratingId);
    }

    @PutMapping(value = "/confirm/{ratingId}")
    public void confirmProductRating(@PathVariable(name = "ratingId") int ratingId) {
        productRatingService.confirmProductRating(ratingId);
    }
}
