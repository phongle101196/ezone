package com.ezone.controller;

import com.ezone.dto.ProductSubCommentDTO;
import com.ezone.entity.ProductSubComment;
import com.ezone.form.create.CreatingProductSubCommentForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingProductSubCommentForm;
import com.ezone.service.IProductSubCommentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/productSubComments")
@CrossOrigin("*")
public class ProductSubCommentController {
    @Autowired
    private IProductSubCommentService productSubCommentService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<ProductSubCommentDTO> getAllProductSubComment(Pageable pageable, FilterForm form) {
        Page<ProductSubComment> productSubCommentPage = productSubCommentService.getAllProductSubComment(pageable, form);
        List<ProductSubComment> productSubComments = productSubCommentPage.getContent();
        List<ProductSubCommentDTO> productSubCommentDTOS = modelMapper.map(productSubComments, new TypeToken<List<ProductSubCommentDTO>>() {
        }.getType());
        return new PageImpl<>(productSubCommentDTOS, pageable, productSubCommentPage.getTotalElements());
    }

    @GetMapping(value = "/{subCommentId}")
    public ProductSubCommentDTO getProductSubCommentById(@PathVariable(name = "subCommentId") int subCommentId) {
        return modelMapper.map(productSubCommentService.getProductSubCommentById(subCommentId), ProductSubCommentDTO.class);
    }

    @PostMapping
    public void createNewProductSubComment(@RequestBody CreatingProductSubCommentForm form) {
        productSubCommentService.createNewProductSubComment(form);
    }

    @PutMapping(value = "/{subCommentId}")
    public void updateProductSubComment(@PathVariable(name = "subCommentId") int subCommentId, @RequestBody UpdatingProductSubCommentForm form) {
        form.setId(subCommentId);
        productSubCommentService.updateProductSubComment(form);
    }

    @DeleteMapping(value = "/{subCommentId}")
    public void deleteProductSubCommentById(@PathVariable(name = "subCommentId") int subCommentId) {
        productSubCommentService.deleteProductSubCommentById(subCommentId);
    }

}
