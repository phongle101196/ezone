package com.ezone.service;

import com.ezone.entity.NewsSubComment;
import com.ezone.form.create.CreatingNewsSubCommentForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingNewsSubCommentForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface INewsSubCommentService {
    Page<NewsSubComment> getAllNewsSubComment(Pageable pageable, FilterForm form);

    void createNewNewsSubComment(CreatingNewsSubCommentForm form);

    void updateNewsSubComment(UpdatingNewsSubCommentForm form);

    void deleteNewsSubCommentById(int subCommentId);

    NewsSubComment findById(int id);
}
