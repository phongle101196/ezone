package com.ezone.service;

import com.ezone.entity.NewsPostLike;
import com.ezone.form.create.CreatingNewsPostLikeForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.repository.INewsPostLikeRepository;
import com.ezone.specification.NewsPostLikeSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsPostLikeService implements INewsPostLikeService {
    @Autowired
    private INewsPostLikeRepository newsPostLikeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<NewsPostLike> getAllNewsPostLikeByPostId(FilterForm form) {
        Specification<NewsPostLike> where = NewsPostLikeSpecification.buildWherePost(form);
        return newsPostLikeRepository.findAll(where);
    }

    @Override
    public NewsPostLike getNewsPostLikeByPostAndUser(FilterForm form) {
        Specification<NewsPostLike> where = NewsPostLikeSpecification.buildWhereUserAndPost(form);
        List<NewsPostLike> list = newsPostLikeRepository.findAll(where);
        if (list.size() == 1) {
            return newsPostLikeRepository.findById(list.get(0).getId()).get();
        } else {
            return null;
        }
    }

    @Override
    public void createNewNewsPostLike(CreatingNewsPostLikeForm form) {
        TypeMap<CreatingNewsPostLikeForm, NewsPostLike> typeMap = modelMapper.getTypeMap(CreatingNewsPostLikeForm.class, NewsPostLike.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingNewsPostLikeForm, NewsPostLike>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        NewsPostLike newsPostLike = modelMapper.map(form, NewsPostLike.class);
        newsPostLikeRepository.save(newsPostLike);
    }

    @Override
    public void deleteNewsPostLikeById(int postLikeId) {
        newsPostLikeRepository.deleteById(postLikeId);
    }

    @Override
    public NewsPostLike findById(int id) {
        return newsPostLikeRepository.findById(id).get();
    }
}
