package com.ezone.service;

import com.ezone.entity.NewsTopic;
import com.ezone.form.create.CreatingNewsTopicForm;
import com.ezone.form.update.UpdatingNewsTopicForm;
import com.ezone.repository.INewsTopicRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsTopicService implements INewsTopicService {
    @Autowired
    private INewsTopicRepository newsTopicRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<NewsTopic> getAllNewsTopic() {
        return newsTopicRepository.findAll();
    }

    @Override
    public void createNewNewsTopic(CreatingNewsTopicForm form) {
        NewsTopic newsTopic = modelMapper.map(form, NewsTopic.class);
        newsTopicRepository.save(newsTopic);
    }

    @Override
    public void updateNewsTopic(UpdatingNewsTopicForm form) {
        NewsTopic newsTopic = modelMapper.map(form, NewsTopic.class);
        newsTopicRepository.save(newsTopic);
    }

    @Override
    public void deleteNewsTopicById(int topicId) {
        newsTopicRepository.deleteById(topicId);
    }

    @Override
    public NewsTopic findById(int id) {
        return newsTopicRepository.findById(id).get();
    }
}
