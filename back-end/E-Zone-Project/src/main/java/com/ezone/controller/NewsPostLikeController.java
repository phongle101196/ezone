package com.ezone.controller;

import com.ezone.dto.NewsPostLikeDTO;
import com.ezone.entity.NewsPostLike;
import com.ezone.form.create.CreatingNewsPostLikeForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.service.INewsPostLikeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/newsPostLikes")
@CrossOrigin("*")
public class NewsPostLikeController {
    @Autowired
    private INewsPostLikeService newsPostLikeService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<NewsPostLikeDTO> getAllNewsPostLikeByPostId(FilterForm form) {
        List<NewsPostLike> newsPostLikes = newsPostLikeService.getAllNewsPostLikeByPostId(form);
        return modelMapper.map(newsPostLikes, new TypeToken<List<NewsPostLikeDTO>>() {
        }.getType());
    }

    @GetMapping(value = "/get")
    public NewsPostLikeDTO getNewsPostLikeByPostAndUser(FilterForm form) {
        NewsPostLike newsPostLike = newsPostLikeService.getNewsPostLikeByPostAndUser(form);
        return modelMapper.map(newsPostLike, NewsPostLikeDTO.class);
    }

    @PostMapping
    public void createNewNewsPostLike(@RequestBody CreatingNewsPostLikeForm form) {
        newsPostLikeService.createNewNewsPostLike(form);
    }

    @DeleteMapping(value = "/{postLikeId}")
    public void deleteNewsPostLikeById(@PathVariable(name = "postLikeId") int postLikeId) {
        newsPostLikeService.deleteNewsPostLikeById(postLikeId);
    }

    @GetMapping(value = "/{id}")
    public NewsPostLikeDTO findById(@PathVariable(name = "id") int id) {
        return modelMapper.map(newsPostLikeService.findById(id), NewsPostLikeDTO.class);
    }
}
