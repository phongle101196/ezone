package com.ezone.controller;

import com.ezone.dto.NewsCommentDTO;
import com.ezone.entity.NewsComment;
import com.ezone.form.create.CreatingNewsCommentForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingNewsCommentForm;
import com.ezone.service.INewsCommentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/newsComments")
@CrossOrigin("*")
public class NewsCommentController {
    @Autowired
    private INewsCommentService newsCommentService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<NewsCommentDTO> getAllNewsComment(Pageable pageable, FilterForm form) {
        Page<NewsComment> newsCommentPage = newsCommentService.getAllNewsComment(pageable, form);
        List<NewsComment> newsComments = newsCommentPage.getContent();
        List<NewsCommentDTO> newsCommentDTOS = modelMapper.map(newsComments, new TypeToken<List<NewsCommentDTO>>() {
        }.getType());
        return new PageImpl<>(newsCommentDTOS, pageable, newsCommentPage.getTotalElements());
    }

    @PostMapping
    public void createNewNewsComment(@RequestBody CreatingNewsCommentForm form) {
        newsCommentService.createNewNewsComment(form);
    }

    @PutMapping(value = "/{commentId}")
    public void updateNewsComment(@PathVariable(name = "commentId") int commentId, @RequestBody UpdatingNewsCommentForm form) {
        form.setId(commentId);
        newsCommentService.updateNewsComment(form);
    }

    @DeleteMapping(value = "/{commentId}")
    public void deleteNewsCommentById(@PathVariable(name = "commentId") int commentId) {
        newsCommentService.deleteNewsCommentById(commentId);
    }

    @GetMapping(value = "/{id}")
    public NewsCommentDTO findById(@PathVariable(name = "id") int id) {
        return modelMapper.map(newsCommentService.findById(id), NewsCommentDTO.class);
    }
}
