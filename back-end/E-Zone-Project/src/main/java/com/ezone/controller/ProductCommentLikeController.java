package com.ezone.controller;

import com.ezone.dto.ProductCommentLikeDTO;
import com.ezone.entity.ProductCommentLike;
import com.ezone.form.create.CreatingProductCommentLikeForm;
import com.ezone.service.IProductCommentLikeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/productCommentLikes")
@CrossOrigin("*")
public class ProductCommentLikeController {
    @Autowired
    private IProductCommentLikeService productCommentLikeService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/comment/{commentId}")
    public List<ProductCommentLikeDTO> getAllProductCommentLikeByCommentId(@PathVariable(name = "commentId") int commentId) {
        List<ProductCommentLike> productCommentLikes = productCommentLikeService.getAllProductCommentLikeByCommentId(commentId);
        return modelMapper.map(productCommentLikes, new TypeToken<List<ProductCommentLikeDTO>>() {
        }.getType());
    }

    @GetMapping(value = "/{commentLikeId")
    public ProductCommentLikeDTO getProductCommentLikeById(@PathVariable(name = "commentLikeId") int commentLikeId) {
        return modelMapper.map(productCommentLikeService.getProductCommentLikeById(commentLikeId), ProductCommentLikeDTO.class);
    }

    @GetMapping
    public ProductCommentLikeDTO getProductCommentLikeByCommentIdAndUserId(@RequestParam int commentId, @RequestParam int userId) {
        return modelMapper.map(productCommentLikeService.getProductCommentLikeByCommentIdAndUserId(commentId, userId), ProductCommentLikeDTO.class);
    }

    @PostMapping
    public void createMewProductCommentLike(CreatingProductCommentLikeForm form) {
        productCommentLikeService.createMewProductCommentLike(form);
    }

    @DeleteMapping(value = "/{commentLikeId")
    public void deleteProductCommentLikeById(@PathVariable(name = "commentLikeId") int commentLikeId) {
        productCommentLikeService.deleteProductCommentLikeById(commentLikeId);
    }
}
