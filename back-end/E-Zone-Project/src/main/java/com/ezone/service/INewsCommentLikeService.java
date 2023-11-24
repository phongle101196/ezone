package com.ezone.service;

import com.ezone.entity.NewsCommentLike;
import com.ezone.form.create.CreatingNewsCommentLikeForm;
import com.ezone.form.filter.FilterForm;

import java.util.List;

public interface INewsCommentLikeService {
    List<NewsCommentLike> getAllNewsCommentLikeByPostCommentId(FilterForm form);

    NewsCommentLike getNewsCommentLikeByUserIdAndPostCommentId(FilterForm form);

    void createNewNewsCommentLike(CreatingNewsCommentLikeForm form);

    void deleteNewsCommentLikeById(int newsCommentId);

    NewsCommentLike findById(int id);
}
