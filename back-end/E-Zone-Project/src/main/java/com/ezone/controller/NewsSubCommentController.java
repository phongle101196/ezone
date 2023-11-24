package com.ezone.controller;

import com.ezone.dto.NewsSubCommentDTO;
import com.ezone.entity.NewsSubComment;
import com.ezone.form.create.CreatingNewsSubCommentForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingNewsSubCommentForm;
import com.ezone.service.INewsSubCommentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/newsSubComments")
@CrossOrigin("*")
public class NewsSubCommentController {
    @Autowired
    private INewsSubCommentService newsSubCommentService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<NewsSubCommentDTO> getAllNewsSubComment(Pageable pageable, FilterForm form) {
        Page<NewsSubComment> newsSubCommentPage = newsSubCommentService.getAllNewsSubComment(pageable, form);
        List<NewsSubComment> newsSubComments = newsSubCommentPage.getContent();
        List<NewsSubCommentDTO> newsSubCommentDTOS = modelMapper.map(newsSubComments, new TypeToken<List<NewsSubCommentDTO>>() {
        }.getType());
        return new PageImpl<>(newsSubCommentDTOS, pageable, newsSubCommentPage.getTotalElements());
    }

    @PostMapping
    public void createNewNewsSubComment(@RequestBody CreatingNewsSubCommentForm form) {
        newsSubCommentService.createNewNewsSubComment(form);
    }

    @PutMapping(value = "/{subCommentId}")
    public void updateNewsSubComment(@PathVariable(name = "subCommentId") int subCommentId, @RequestBody UpdatingNewsSubCommentForm form) {
        form.setId(subCommentId);
        newsSubCommentService.updateNewsSubComment(form);
    }

    @DeleteMapping(value = "/{subCommentId}")
    public void deleteNewsSubCommentById(@PathVariable(name = "subCommentId") int subCommentId) {
        newsSubCommentService.deleteNewsSubCommentById(subCommentId);
    }

    @GetMapping(value = "/{id}")
    public NewsSubCommentDTO findById(@PathVariable(name = "id") int id) {
        return modelMapper.map(newsSubCommentService.findById(id), NewsSubCommentDTO.class);
    }
}
