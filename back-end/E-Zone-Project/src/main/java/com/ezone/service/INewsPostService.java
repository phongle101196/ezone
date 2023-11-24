package com.ezone.service;

import com.ezone.entity.NewsPost;
import com.ezone.form.create.CreatingNewsPostForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingNewsPostForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface INewsPostService {
    Page<NewsPost> getAllNewsPost(Pageable pageable, FilterForm form);

    void createNewNewsPost(CreatingNewsPostForm form);

    void updateNewsPost(UpdatingNewsPostForm form);

    void deleteNewsPostById(int postId);

    NewsPost findById(int id);
}
