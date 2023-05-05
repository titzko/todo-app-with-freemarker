package com.titzko.freemarkertodo.service;

import com.titzko.freemarkertodo.model.*;
import com.titzko.freemarkertodo.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskServiceTest {

    //https://www.baeldung.com/easymock

    TaskService taskService;
    TaskRepository mockTaskRepository;
    UserService mockUserService;
    MyUser myUser;

    @BeforeEach
    void setUp() {
        mockTaskRepository = mock(TaskRepository.class);
        mockUserService = mock(UserService.class);
        myUser = new MyUser("mati", "pw", "role", true);
        myUser.setId(1L);
        taskService = new TaskService(mockTaskRepository, mockUserService);
    }


    @Test
    void saveTask_withTaskHavingUserId() {
        Task task = new Task(1L, "Test", "test-desc", Priority.MEDIUM, true, null, 1L, Category.FAMILY);

        expect(mockUserService.getUser()).andReturn(myUser);
        expect(mockTaskRepository.save(task)).andReturn(task);
        replay(mockUserService, mockTaskRepository);
        taskService.saveTask(task);
        verify(mockUserService, mockTaskRepository);
    }


    @Test
    void saveTask_withTaskNotHavingUserId() {
        Task task = new Task(null, "Test", "test-desc", Priority.MEDIUM, true, null, null, Category.FAMILY);

        expect(mockUserService.getUser()).andReturn(myUser);
        replay(mockUserService);
        taskService.saveTask(task);
        verify(mockUserService);
    }


    @Test
    void findAllTasks() {
        Task task1 = new Task(null, "Task1", "test-desc", Priority.MEDIUM, true, null, null, Category.FAMILY);
        Task task2 = new Task(null, "Task2", "test-desc", Priority.MEDIUM, true, null, null, Category.FAMILY);
        List<Task> tasks = List.of(task1, task2);

        expect(mockUserService.getUser()).andReturn(myUser);
        expect(mockTaskRepository.findAllByUserId(myUser.getId())).andReturn(tasks);
        replay(mockUserService, mockTaskRepository);
        List<Task> result = taskService.findAllTasks();
        verify(mockUserService, mockTaskRepository);

        assertEquals(tasks, result);
    }

    @Test
    void updateTaskState() throws Exception {
        Task task = new Task(1L, "Task1", "test-desc", Priority.MEDIUM, true, null, 1L, Category.FAMILY);
        Long id = 1L;

        expect(mockUserService.getUser()).andReturn(myUser);
        expect(mockTaskRepository.findById(id)).andReturn(Optional.of(task));
        expect(mockTaskRepository.findById(id)).andReturn(Optional.of(task));
        mockTaskRepository.updateTaskStatus(id);
        replay(mockTaskRepository, mockUserService);
        this.taskService.updateTaskState(id);
        verify(mockUserService, mockTaskRepository);

    }

    @Test
    void deleteTask() throws Exception {
        Task task = new Task(1L, "Task1", "test-desc", Priority.MEDIUM, true, null, 1L, Category.FAMILY);
        Long id = 1L;

        expect(mockUserService.getUser()).andReturn(myUser);
        expect(mockTaskRepository.findById(id)).andReturn(Optional.of(task));
        expect(mockTaskRepository.findById(id)).andReturn(Optional.of(task));
        mockTaskRepository.deleteById(id);
        replay(mockUserService, mockTaskRepository);
        this.taskService.deleteTask(id);
        verify(mockTaskRepository, mockUserService);

    }

    @Test
    void getTaskDates() {
        Task task1 = new Task(null, "Task1", "test-desc", Priority.MEDIUM, true, new Date(2020, 10, 1), null, Category.FAMILY);
        Task task2 = new Task(null, "Task2", "test-desc", Priority.MEDIUM, true, new Date(2020, 10, 1), null, Category.FAMILY);
        List<Task> tasks = List.of(task1, task2);
        Map<Date, DayTaskDto> dayTaskMap = new HashMap<>();
        DayTaskDto dayTaskDto = new DayTaskDto();
        dayTaskDto.setDate(new Date(2020, 10, 1));
        dayTaskDto.setTasks(tasks);
        dayTaskDto.setAmount(2);
        dayTaskMap.put(new Date(2020, 10, 1), dayTaskDto);
        Long id = 1L;

        expect(mockUserService.getUser()).andReturn(myUser);
        expect(mockTaskRepository.findAllByUserId(id)).andReturn(tasks);
        replay(mockUserService, mockTaskRepository);
        Map<Date, DayTaskDto> result = this.taskService.getTaskDates();
        verify(mockUserService, mockTaskRepository);

        assertEquals(dayTaskMap, result);
    }

    @Test
    void getCategoryTaskCount() {
        Task task1 = new Task(null, "Task1", "test-desc", Priority.MEDIUM, true, new Date(2020, 10, 1), null, Category.FAMILY);
        Task task2 = new Task(null, "Task2", "test-desc", Priority.MEDIUM, true, new Date(2020, 10, 1), null, Category.SPORT);
        Task task3 = new Task(null, "Task2", "test-desc", Priority.MEDIUM, true, new Date(2020, 10, 1), null, Category.SPORT);

        List<Task> tasks = List.of(task1, task2, task3);
        Long id = 1L;
        HashMap<Category, Long> map = new HashMap<>();
        map.put(Category.FAMILY, 1L);
        map.put(Category.SPORT, 2L);
        map.put(Category.PERSONAL, 0L);
        map.put(Category.WORK, 0L);


        expect(mockUserService.getUser()).andReturn(myUser);
        expect(mockTaskRepository.findAllByUserId(id)).andReturn(tasks);
        replay(mockUserService, mockTaskRepository);
        Map<Category, Long> result = this.taskService.getCategoryTaskCount();
        verify(mockTaskRepository, mockUserService);

        assertEquals(map, result);
    }
}