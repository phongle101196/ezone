package com.ezone.service;

import com.ezone.entity.NewsPostLike;
import com.ezone.form.create.CreatingNewsPostLikeForm;
import com.ezone.form.filter.FilterForm;

import java.util.List;

public interface INewsPostLikeService {
    List<NewsPostLike> getAllNewsPostLikeByPostId(FilterForm form);

    NewsPostLike getNewsPostLikeByPostAndUser(FilterForm form);

    void createNewNewsPostLike(CreatingNewsPostLikeForm form);

    void deleteNewsPostLikeById(int postLikeId);

    NewsPostLike findById(int id);
}
