package com.ezone.controller;

import com.ezone.entity.NewsPost;
import com.ezone.entity.NewsTopic;
import com.ezone.form.create.CreatingNewsTopicForm;
import com.ezone.form.update.UpdatingNewsTopicForm;
import com.ezone.repository.INewsTopicRepository;
import com.ezone.service.INewsTopicService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/newsTopics")
@CrossOrigin("*")
public class NewsTopicController {
    @Autowired
    private INewsTopicService newsTopicService;

    @GetMapping
    private List<NewsTopic> getAllNewsTopic() {
        return newsTopicService.getAllNewsTopic();
    }

    @PostMapping
    public void createNewNewsTopic(@RequestBody CreatingNewsTopicForm form) {
        newsTopicService.createNewNewsTopic(form);
    }

    @PutMapping(value = "/{topicId}")
    public void updateNewsTopic(@PathVariable(name = "topicId") int topicId, @RequestBody UpdatingNewsTopicForm form) {
        form.setId(topicId);
        newsTopicService.updateNewsTopic(form);
    }

    @DeleteMapping(value = "/{topicId}")
    public void deleteNewsTopicById(@PathVariable(name = "topicId") int topicId) {
        newsTopicService.deleteNewsTopicById(topicId);
    }

    @GetMapping(value = "/{id}")
    public NewsTopic findById(@PathVariable(name = "id") int id) {
        return newsTopicService.findById(id);
    }
}
