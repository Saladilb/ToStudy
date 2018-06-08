package ru.itpark.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ru.itpark.domain.Task;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  private final List<Task> tasks = new ArrayList<>();

  @GetMapping
  @ApiOperation("Get list of Tasks")
  public List<Task> getAll() {
    return tasks;
  }

  @PostMapping
  @ApiOperation("Add new Task")
  public void add(@RequestBody Task task) {
    tasks.add(task);
  }

  @DeleteMapping("/{id}")
  @ApiOperation("Remove Task by id")
  public void delete(@PathVariable int id) {
    // TODO: удалить задачу из СУБД
  }
}
