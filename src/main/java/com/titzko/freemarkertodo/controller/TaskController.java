package com.titzko.freemarkertodo.controller;


import com.titzko.freemarkertodo.exceptions.InvalidTaskException;
import com.titzko.freemarkertodo.model.*;
import com.titzko.freemarkertodo.security.MyUserDetails;
import com.titzko.freemarkertodo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final String TASK_TEMPLATE_PATH = "tasks/tasks";
    private final TaskService taskService;
    private final String TASKS = "tasks";

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @ModelAttribute
    public void addAttributes(Model model) {
        Map<Category, Long> categoryTaskCount = this.taskService.getCategoryTaskCount();
        model.addAttribute("categories", categoryTaskCount);
        model.addAttribute("priorities", Priority.values());
        model.addAttribute("taskDates", this.taskService.getTaskDates());
        MyUser user = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMyUser();
        model.addAttribute("user", user.getUsername());
        model.addAttribute("userId", user.getId());
    }


    @GetMapping("")
    String getTasks(Model model) {
        TaskList taskList = new TaskList();
        List<Task> tasks = this.taskService.findAllTasks();
        taskList.setTasks(tasks);
        model.addAttribute(TASKS, taskList);
        return TASK_TEMPLATE_PATH;
    }


    @PostMapping("/{id}")
    String updateTask(@RequestBody @Valid Task task, BindingResult bindingResult, Model model) {
        TaskList taskList = new TaskList();
        if (bindingResult.hasErrors()) {
            throw new InvalidTaskException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        this.taskService.saveTask(task);
        taskList.setTasks(this.taskService.findAllTasks());
        model.addAttribute(TASKS, taskList);
        return TASK_TEMPLATE_PATH;
    }


    @PutMapping("/{id}")
    String updateTaskStatus(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        taskService.updateTaskState(id);
        return TASK_TEMPLATE_PATH;
    }


    @DeleteMapping("/{id}")
    String deleteTask(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        taskService.deleteTask(id);
        return TASK_TEMPLATE_PATH;
    }


    @GetMapping("/category/{category}")
    String setTaskFilterByCategory(Model model, @PathVariable Category category) {
        TaskList taskList = new TaskList();
        List<Task> tasks = this.taskService.findAllTasks();
        if (category == null) {
            taskList.setTasks(tasks);
        } else {
            model.addAttribute("category", category);
            taskList.setTasks(tasks.stream()
                    .filter(task -> task.getCategory() == category).toList());
        }
        model.addAttribute(TASKS, taskList);
        return TASK_TEMPLATE_PATH;
    }


    @GetMapping(value = {"/date/{date}/{category}"})
    String setTaskFilterByDateAndCategory(Model model,
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") Date date,
                                          @PathVariable Optional<Category> category) {
        TaskList taskList = new TaskList();
        List<Task> tasks = this.taskService.findAllTasks();
        taskList.setTasks(tasks.stream()
                .filter(task -> task.getStartingDate() != null)
                .filter(task -> task.getStartingDate().getTime() == (date.getTime()))
                .filter(task -> task.getCategory().equals(category.get()))
                .toList());
        model.addAttribute("category", category.isPresent() ? category.get() : Category.PERSONAL);
        model.addAttribute("date", date);
        model.addAttribute(TASKS, taskList);
        return TASK_TEMPLATE_PATH;
    }


    @GetMapping(value = {"/date/{date}"})
    String setTaskFilterByDate(Model model,
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") Date date) {
        TaskList taskList = new TaskList();
        List<Task> tasks = this.taskService.findAllTasks();
        taskList.setTasks(tasks.stream()
                .filter(task -> task.getStartingDate() != null)
                .filter(task -> task.getStartingDate().getTime() == (date.getTime()))
                .toList());
        model.addAttribute(TASKS, taskList);
        model.addAttribute("date", date);
        return TASK_TEMPLATE_PATH;
    }
}
