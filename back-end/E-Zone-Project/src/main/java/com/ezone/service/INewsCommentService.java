package com.ezone.service;

import com.ezone.entity.NewsComment;
import com.ezone.form.create.CreatingNewsCommentForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingNewsCommentForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface INewsCommentService {
    Page<NewsComment> getAllNewsComment(Pageable pageable, FilterForm form);

    void createNewNewsComment(CreatingNewsCommentForm form);

    void updateNewsComment(UpdatingNewsCommentForm form);

    void deleteNewsCommentById(int commentId);

    NewsComment findById(int id);
}
