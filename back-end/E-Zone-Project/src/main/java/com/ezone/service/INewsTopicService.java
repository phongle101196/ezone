package com.ezone.service;

import com.ezone.entity.NewsTopic;
import com.ezone.form.create.CreatingNewsTopicForm;
import com.ezone.form.update.UpdatingNewsTopicForm;

import java.util.List;

public interface INewsTopicService {
    List<NewsTopic> getAllNewsTopic();

    void createNewNewsTopic(CreatingNewsTopicForm form);

    void updateNewsTopic(UpdatingNewsTopicForm form);

    void deleteNewsTopicById(int topicId);

    NewsTopic findById(int id);
}
