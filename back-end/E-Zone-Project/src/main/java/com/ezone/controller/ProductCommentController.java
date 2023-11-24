package com.ezone.controller;

import com.ezone.dto.ProductCommentDTO;
import com.ezone.entity.ProductComment;
import com.ezone.form.create.CreatingProductCommentForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingProductCommentForm;
import com.ezone.service.IProductCommentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/productComments")
@CrossOrigin("*")
public class ProductCommentController {
    @Autowired
    private IProductCommentService productCommentService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<ProductCommentDTO> getAllProductComment(Pageable pageable, FilterForm form) {
        Page<ProductComment> productCommentPage = productCommentService.getAllProductComment(pageable, form);
        List<ProductComment> productComments = productCommentPage.getContent();
        List<ProductCommentDTO> productCommentDTOS = modelMapper.map(productComments, new TypeToken<List<ProductCommentDTO>>() {
        }.getType());
        return new PageImpl<>(productCommentDTOS, pageable, productCommentPage.getTotalElements());
    }

    @GetMapping(value = "/{commentId}")
    public ProductCommentDTO getProductCommentById(@PathVariable(name = "commentId") int commentId) {
        return modelMapper.map(productCommentService.getProductCommentById(commentId), ProductCommentDTO.class);
    }

    @PostMapping
    public void createNewProductComment(@RequestBody CreatingProductCommentForm form) {
        productCommentService.createNewProductComment(form);
    }

    @PutMapping(value = "/{commentId}")
    public void updateProductComment(@PathVariable(name = "commentId") int commentId, @RequestBody UpdatingProductCommentForm form) {
        form.setId(commentId);
        productCommentService.updateProductComment(form);
    }

    @DeleteMapping(value = "/{commentId}")
    public void deleteProductCommentById(@PathVariable(name = "commentId") int commentId) {
        productCommentService.deleteProductCommentById(commentId);
    }

    @PutMapping(value = "/confirm/{commentId}")
    public void confirmProductComment(@PathVariable(name = "commentId") int commentId) {
        productCommentService.confirmProductComment(commentId);
    }
}
