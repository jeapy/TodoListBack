package jpz.application.todo.Controller;


import jakarta.validation.Valid;
import jpz.application.todo.Model.Todo;
import jpz.application.todo.Service.TodoService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoController {
    private final TodoService todoService;

    // Create a new todo
    @PostMapping
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody Todo todo) {
        Todo createdTodo = todoService.createTodo(todo);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    // Get all todos
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    // Get todo by ID
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id)
                .map(todo -> new ResponseEntity<>(todo, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a todo
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @Valid @RequestBody Todo todoDetails) {
        Todo updatedTodo = todoService.updateTodo(id, todoDetails);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    // Update a todo
    @PutMapping("/{id}/completed")
    public ResponseEntity<Todo> completedTodo(@PathVariable Long id) {
        Todo updatedTodo = todoService.completed(id);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    // Delete a todo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get completed todos
    @GetMapping("/completed")
    public ResponseEntity<List<Todo>> getCompletedTodos() {
        List<Todo> completedTodos = todoService.getCompletedTodos();
        return new ResponseEntity<>(completedTodos, HttpStatus.OK);
    }

    // Get pending todos
    @GetMapping("/pending")
    public ResponseEntity<List<Todo>> getPendingTodos() {
        List<Todo> pendingTodos = todoService.getPendingTodos();
        return new ResponseEntity<>(pendingTodos, HttpStatus.OK);
    }


}