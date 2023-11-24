package com.ezone.service;

import com.ezone.entity.NewsPost;
import com.ezone.form.create.CreatingNewsPostForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingNewsPostForm;
import com.ezone.repository.INewsPostRepository;
import com.ezone.specification.NewsPostSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class NewsPostService implements INewsPostService {
    @Autowired
    private INewsPostRepository newsPostRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<NewsPost> getAllNewsPost(Pageable pageable, FilterForm form) {
        Specification<NewsPost> where = NewsPostSpecification.buildWhere(form);
        return newsPostRepository.findAll(where, pageable);
    }

    @Override
    public void createNewNewsPost(CreatingNewsPostForm form) {
        TypeMap<CreatingNewsPostForm, NewsPost> typeMap = modelMapper.getTypeMap(CreatingNewsPostForm.class, NewsPost.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingNewsPostForm, NewsPost>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }

        NewsPost newsPost = modelMapper.map(form, NewsPost.class);
        newsPostRepository.save(newsPost);
    }

    @Override
    public void updateNewsPost(UpdatingNewsPostForm form) {
        NewsPost tmp = newsPostRepository.findById(form.getId()).get();

        NewsPost newsPost = modelMapper.map(form, NewsPost.class);
        newsPost.setUser(tmp.getUser());
        newsPost.setTopic(tmp.getTopic());
        newsPost.setCreatedDate(tmp.getCreatedDate());

        newsPostRepository.save(newsPost);
    }

    @Override
    public void deleteNewsPostById(int postId) {
        newsPostRepository.deleteById(postId);
    }

    @Override
    public NewsPost findById(int id) {
        return newsPostRepository.findById(id).get();
    }
}
