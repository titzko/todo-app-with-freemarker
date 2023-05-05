package com.titzko.freemarkertodo.service;

import com.titzko.freemarkertodo.model.Category;
import com.titzko.freemarkertodo.model.DayTaskDto;
import com.titzko.freemarkertodo.model.MyUser;
import com.titzko.freemarkertodo.model.Task;
import com.titzko.freemarkertodo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {

    TaskRepository taskRepository;
    UserService userService;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public void saveTask(Task task) {
        MyUser user = userService.getUser();
        if (task.getUserId() == null) {
            task.setUserId(user.getId());
            this.taskRepository.save(task);
        } else {
            if (Objects.equals(task.getUserId(), user.getId())) {
                this.taskRepository.save(task);
            }
        }
    }

    public List<Task> findAllTasks() {
        MyUser user = userService.getUser();
        return this.taskRepository.findAllByUserId(user.getId());
    }

    public void updateTaskState(Long id) throws ChangeSetPersister.NotFoundException {
        MyUser user = userService.getUser();
        if (this.taskRepository.findById(id).isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        Task task = this.taskRepository.findById(id).get();
        if (Objects.equals(task.getUserId(), user.getId())) {
            this.taskRepository.updateTaskStatus(id);
        }
    }

    public void deleteTask(Long id) throws ChangeSetPersister.NotFoundException {
        MyUser user = userService.getUser();
        if (this.taskRepository.findById(id).isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        Task task = this.taskRepository.findById(id).get();
        if (Objects.equals(task.getUserId(), user.getId())) {
            this.taskRepository.deleteById(id);
        }
    }

    public Map<Date, DayTaskDto> getTaskDates() {
        MyUser user = userService.getUser();
        Map<Date, DayTaskDto> map = new HashMap<>();
        List<Task> tasks = this.taskRepository.findAllByUserId(user.getId());
        Set<Date> dates = tasks.stream().map(Task::getStartingDate).collect(Collectors.toSet());

        for(Date date: dates) {
            long amount = tasks.stream()
                    .filter(task -> task.getStartingDate() != null)
                    .filter(task -> task.getStartingDate().equals(date)).count();

            List<Task> dailyTasks = tasks.stream()
                    .filter(task -> task.getStartingDate() != null)
                    .filter(task -> task.getStartingDate().equals(date)).toList();

            if (amount > 0) {
                DayTaskDto dayTaskDto = new DayTaskDto();
                dayTaskDto.setDate(date);
                dayTaskDto.setTasks(dailyTasks);
                dayTaskDto.setAmount((int) amount);
                map.put(date, dayTaskDto);
            }
        }
        return map;
    }

    public Map<Category, Long> getCategoryTaskCount() {
        MyUser user = userService.getUser();
        EnumMap<Category, Long> map = new EnumMap<>(Category.class);
        List<Category> categories = List.of(Category.values());
        List<Task> tasks = this.taskRepository.findAllByUserId(user.getId());

        for(Category category: categories) {
            Long amount = tasks.stream()
                    .filter(task -> task.getCategory() == category)
                    .count();
            map.put(category,amount);
        }
        return map;
    }



}
