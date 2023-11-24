package com.ezone.controller;

import com.ezone.dto.NewsPostDTO;
import com.ezone.entity.NewsPost;
import com.ezone.form.create.CreatingNewsPostForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingNewsPostForm;
import com.ezone.service.INewsPostService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/newsPosts")
@CrossOrigin("*")
public class NewsPostController {
    @Autowired
    private INewsPostService newsPostService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<NewsPostDTO> getAllNewsPost(Pageable pageable, FilterForm form) {
        Page<NewsPost> newsPostPage = newsPostService.getAllNewsPost(pageable, form);
        List<NewsPost> newsPosts = newsPostPage.getContent();
        List<NewsPostDTO> newsPostDTOS = modelMapper.map(newsPosts, new TypeToken<List<NewsPostDTO>>() {
        }.getType());

        return new PageImpl<>(newsPostDTOS, pageable, newsPostPage.getTotalElements());
    }

    @PostMapping
    public void createNewNewsPost(@RequestBody CreatingNewsPostForm form) {
        newsPostService.createNewNewsPost(form);
    }

    @PutMapping(value = "/{postId}")
    public void updateNewsPost(@PathVariable(name = "postId") int postId, @RequestBody UpdatingNewsPostForm form) {
        form.setId(postId);
        newsPostService.updateNewsPost(form);
    }

    @DeleteMapping(value = "/{postId}")
    public void deleteNewsPostById(@PathVariable(name = "postId") int postId) {
        newsPostService.deleteNewsPostById(postId);
    }

    @GetMapping(value = "/{id}")
    public NewsPostDTO findById(@PathVariable(name = "id") int id) {
        return modelMapper.map(newsPostService.findById(id), NewsPostDTO.class);
    }
}
