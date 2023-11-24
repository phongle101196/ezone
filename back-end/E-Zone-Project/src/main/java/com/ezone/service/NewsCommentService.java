package com.ezone.service;

import com.ezone.entity.NewsComment;
import com.ezone.form.create.CreatingNewsCommentForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingNewsCommentForm;
import com.ezone.repository.INewsCommentRepository;
import com.ezone.specification.NewsCommentSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class NewsCommentService implements INewsCommentService {
    @Autowired
    private INewsCommentRepository newsCommentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<NewsComment> getAllNewsComment(Pageable pageable, FilterForm form) {
        Specification<NewsComment> where = NewsCommentSpecification.buildWhere(form);
        return newsCommentRepository.findAll(where, pageable);
    }

    @Override
    public void createNewNewsComment(CreatingNewsCommentForm form) {
        TypeMap<CreatingNewsCommentForm, NewsComment> typeMap = modelMapper.getTypeMap(CreatingNewsCommentForm.class, NewsComment.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingNewsCommentForm, NewsComment>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }

        NewsComment newsComment = modelMapper.map(form, NewsComment.class);
        newsCommentRepository.save(newsComment);
    }

    @Override
    public void updateNewsComment(UpdatingNewsCommentForm form) {
        //Get comment
        NewsComment tmp = newsCommentRepository.findById(form.getId()).get();

        //Create updated content comment
        NewsComment newsComment = modelMapper.map(form, NewsComment.class);

        //Keep origin post, user and created date
        newsComment.setUser(tmp.getUser());
        newsComment.setPost(tmp.getPost());
        newsComment.setCreatedDate(tmp.getCreatedDate());

        //Save the update
        newsCommentRepository.save(newsComment);
    }

    @Override
    public void deleteNewsCommentById(int commentId) {
        newsCommentRepository.deleteById(commentId);
    }

    @Override
    public NewsComment findById(int id) {
        return newsCommentRepository.findById(id).get();
    }
}
