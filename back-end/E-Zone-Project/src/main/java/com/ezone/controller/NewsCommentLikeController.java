package com.ezone.controller;

import com.ezone.dto.NewsCommentLikeDTO;
import com.ezone.entity.NewsCommentLike;
import com.ezone.form.create.CreatingNewsCommentLikeForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.service.INewsCommentLikeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/newsCommentLikes")
@CrossOrigin("*")
public class NewsCommentLikeController {
    @Autowired
    private INewsCommentLikeService newsCommentLikeService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<NewsCommentLikeDTO> getAllNewsCommentLikeByPostCommentId(FilterForm form) {
        List<NewsCommentLike> newsCommentLikes = newsCommentLikeService.getAllNewsCommentLikeByPostCommentId(form);
        return modelMapper.map(newsCommentLikes, new TypeToken<List<NewsCommentLikeDTO>>() {
        }.getType());
    }

    @GetMapping(value = "/get")
    public NewsCommentLikeDTO getNewsCommentLikeByUserIdAndPostCommentId(FilterForm form) {
        NewsCommentLike newsCommentLike = newsCommentLikeService.getNewsCommentLikeByUserIdAndPostCommentId(form);
        return modelMapper.map(newsCommentLike, NewsCommentLikeDTO.class);
    }

    @PostMapping
    public void createNewNewsCommentLike(@RequestBody CreatingNewsCommentLikeForm form) {
        newsCommentLikeService.createNewNewsCommentLike(form);
    }

    @DeleteMapping(value = "/{newsCommentLikeId}")
    public void deleteNewsCommentLikeById(@PathVariable(name = "newsCommentLikeId") int newsCommentLikeId) {
        newsCommentLikeService.deleteNewsCommentLikeById(newsCommentLikeId);
    }

    @GetMapping(value = "/{id}")
    public NewsCommentLikeDTO findById(@PathVariable(name = "id") int id) {
        return modelMapper.map(newsCommentLikeService.findById(id), NewsCommentLikeDTO.class);
    }
}
