package com.ezone.service;

import com.ezone.entity.NewsComment;
import com.ezone.entity.NewsSubComment;
import com.ezone.form.create.CreatingNewsCommentForm;
import com.ezone.form.create.CreatingNewsSubCommentForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingNewsSubCommentForm;
import com.ezone.repository.INewsSubCommentRepository;
import com.ezone.specification.NewsSubCommentSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class NewsSubCommentService implements INewsSubCommentService {
    @Autowired
    private INewsSubCommentRepository newsSubCommentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<NewsSubComment> getAllNewsSubComment(Pageable pageable, FilterForm form) {
        Specification<NewsSubComment> where = NewsSubCommentSpecification.buildWhere(form);
        return newsSubCommentRepository.findAll(where, pageable);
    }

    @Override
    public void createNewNewsSubComment(CreatingNewsSubCommentForm form) {
        TypeMap<CreatingNewsSubCommentForm, NewsSubComment> typeMap = modelMapper.getTypeMap(CreatingNewsSubCommentForm.class, NewsSubComment.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingNewsSubCommentForm, NewsSubComment>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        NewsSubComment newsSubComment = modelMapper.map(form, NewsSubComment.class);
        newsSubCommentRepository.save(newsSubComment);
    }

    @Override
    public void updateNewsSubComment(UpdatingNewsSubCommentForm form) {
        NewsSubComment tmp = newsSubCommentRepository.findById(form.getId()).get();
        NewsSubComment newsSubComment = modelMapper.map(form, NewsSubComment.class);
        newsSubComment.setUser(tmp.getUser());
        newsSubComment.setPostComment(tmp.getPostComment());
        newsSubComment.setCreatedDate(tmp.getCreatedDate());
        newsSubCommentRepository.save(newsSubComment);
    }

    @Override
    public void deleteNewsSubCommentById(int subCommentId) {
        newsSubCommentRepository.deleteById(subCommentId);
    }

    @Override
    public NewsSubComment findById(int id) {
        return newsSubCommentRepository.findById(id).get();
    }
}
