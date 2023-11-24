package com.ezone.service;

import com.ezone.entity.NewsCommentLike;
import com.ezone.form.create.CreatingNewsCommentLikeForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.repository.INewsCommentLikeRepository;
import com.ezone.specification.NewsCommentLikeSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsCommentLikeService implements INewsCommentLikeService {
    @Autowired
    private INewsCommentLikeRepository newsCommentLikeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<NewsCommentLike> getAllNewsCommentLikeByPostCommentId(FilterForm form) {
        Specification<NewsCommentLike> where = NewsCommentLikeSpecification.buildWhereComment(form);
        return newsCommentLikeRepository.findAll(where);
    }

    @Override
    public NewsCommentLike getNewsCommentLikeByUserIdAndPostCommentId(FilterForm form) {
        Specification<NewsCommentLike> where = NewsCommentLikeSpecification.buildWhereUserAndComment(form);
        List<NewsCommentLike> list = newsCommentLikeRepository.findAll(where);

        //NewsCommentLike unique key (news_comment_id, user_id) -> only 1 or 0 result in list
        if (list.size() == 1) {
            return newsCommentLikeRepository.findById(list.get(0).getId()).get();
        } else {
            return null;
        }
    }

    @Override
    public void createNewNewsCommentLike(CreatingNewsCommentLikeForm form) {
        TypeMap<CreatingNewsCommentLikeForm, NewsCommentLike> typeMap = modelMapper.getTypeMap(CreatingNewsCommentLikeForm.class, NewsCommentLike.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingNewsCommentLikeForm, NewsCommentLike>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }

        NewsCommentLike newsCommentLike = modelMapper.map(form, NewsCommentLike.class);
        newsCommentLikeRepository.save(newsCommentLike);
    }

    @Override
    public void deleteNewsCommentLikeById(int newsCommentLikeId) {
        newsCommentLikeRepository.deleteById(newsCommentLikeId);
    }

    @Override
    public NewsCommentLike findById(int id) {
        return newsCommentLikeRepository.findById(id).get();
    }
}
